package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.pays.requestDto.SalaryDetailRequest;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.borders.SolidBorder;
import java.math.BigDecimal;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PdfGenerationService {

    private static final String KOREAN_FONT_PATH = "classpath:/fonts/NanumGothic-Regular.ttf";
    private final ResourceLoader resourceLoader;

    @Autowired
    public PdfGenerationService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public byte[] generateSalarySlipPdf(String employeeName, List<SalaryDetailRequest> salaryDetails) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // 폰트 설정
        Resource resource = resourceLoader.getResource(KOREAN_FONT_PATH);
        File tempFontFile = Files.createTempFile("nanumfont", ".ttf").toFile();
        try (InputStream inputStream = resource.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(tempFontFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        }
        PdfFont koreanFont = PdfFontFactory.createFont(
                tempFontFile.getAbsolutePath(),
                PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );
        document.setFont(koreanFont);

        String payDateStr = new SimpleDateFormat("yyyy-MM-dd").format(salaryDetails.get(0).getPayDate());
        String payMonth = new SimpleDateFormat("MM월").format(salaryDetails.get(0).getPayDate());

        // 제목
        document.add(new Paragraph(payMonth + " 급여명세서")
                .setFontSize(22)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(15));

        // 기본 정보 테이블
        String department = salaryDetails.get(0).getDepartmentName();
        String position = salaryDetails.get(0).getGradeName();
        Timestamp joinDate = salaryDetails.get(0).getHireDate();
        // 날짜 포맷팅
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 형식으로 설정
        String formattedJoinDate = sdf.format(joinDate);

        Table infoTable = new Table(new float[]{100, 200, 100, 200});
        infoTable.setWidth(UnitValue.createPercentValue(100));
        infoTable.addCell(createHeaderCell("이름"));
        infoTable.addCell(createInfoCell(employeeName));
        infoTable.addCell(createHeaderCell("직급"));
        infoTable.addCell(createInfoCell(position));
        System.out.println("직급 : " + position);
        infoTable.addCell(createHeaderCell("부서"));
        infoTable.addCell(createInfoCell(department));
        System.out.println("부서 : " + department);
        infoTable.addCell(createHeaderCell("입사일"));
        infoTable.addCell(createInfoCell(formattedJoinDate));
        infoTable.setMarginBottom(20);
        document.add(infoTable);

        // 세부 급여 테이블 (좌측: 지급, 우측: 공제)
        Table detailTable = new Table(new float[]{100, 100, 100, 100});
        detailTable.setWidth(UnitValue.createPercentValue(100));

// 헤더: 지급 항목(2칸 병합), 공제 항목(2칸 병합)
        detailTable.addCell(createSummaryHeaderCell("지급 항목", 2));
        detailTable.addCell(createSummaryHeaderCell("공제 항목", 2));

// 더미 데이터 추가
        BigDecimal totalPay = BigDecimal.ZERO;
        BigDecimal totalDeduction = BigDecimal.ZERO;

        BigDecimal bonus1 = new BigDecimal("1000000");  // 보너스 예시
        BigDecimal bonus2 = new BigDecimal(String.valueOf(salaryDetails.get(0).getBonus()));   // 보너스 예시
        BigDecimal deductions1 = new BigDecimal("150000");  // 공제 예시 (세금)
        BigDecimal deductions2 = new BigDecimal("200000");  // 공제 예시 (기존 보험료)

// 총합 계산
        totalPay = totalPay.add(salaryDetails.get(0).getBasePay()).add(salaryDetails.get(0).getBonus());
//        totalDeduction = totalDeduction.add(deductions1).add(deductions2);

// 기본급, 세금, 보너스 항목 추가
        detailTable.addCell(createCell("기본급"));
        detailTable.addCell(createCell(formatMoney(salaryDetails.get(0).getBasePay())));
        detailTable.addCell(createCell("세금"));
        detailTable.addCell(createCell(formatMoney(salaryDetails.get(0).getDeductions())));

// 보너스 추가
        detailTable.addCell(createCell("보너스"));
        detailTable.addCell(createCell(formatMoney(bonus2)));

// 삭제된 보험료 항목을 제외한 공제 항목 추가
        detailTable.addCell(createCell("공제 총액"));
        detailTable.addCell(createCell(formatMoney(salaryDetails.get(0).getDeductions())));

// 마지막 줄: 총계
        detailTable.addCell(createHeaderCell("지급 합계"));
        detailTable.addCell(createHeaderCell(formatMoney(totalPay)));
        detailTable.addCell(createHeaderCell("공제 합계"));
        detailTable.addCell(createHeaderCell(formatMoney(salaryDetails.get(0).getDeductions())));

        document.add(detailTable.setMarginBottom(20));

        BigDecimal finalPay = totalPay.subtract(totalDeduction);

// 요약 테이블
        Table summaryTable = new Table(new float[]{300, 200});
        summaryTable.setWidth(UnitValue.createPercentValue(100));
        summaryTable.addHeaderCell(createSummaryHeaderCell("요약 항목"));
        summaryTable.addHeaderCell(createSummaryHeaderCell("합계 (원)"));

        summaryTable.addCell(createSummaryCell("지급 총액"));
        summaryTable.addCell(createSummaryCell(formatMoney(totalPay)));
        summaryTable.addCell(createSummaryCell("공제 총액"));
        summaryTable.addCell(createSummaryCell(formatMoney(salaryDetails.get(0).getDeductions())));
        summaryTable.addCell(createSummaryCell("실 지급액"));
        summaryTable.addCell(createSummaryCell(formatMoney(salaryDetails.get(0).getFinalPay())));

        document.add(summaryTable.setMarginBottom(30));


        // 하단 문구
        document.add(new Paragraph("지급일: " + payDateStr)
                .setFontSize(9)
                .setFontColor(ColorConstants.GRAY)
                .setTextAlignment(TextAlignment.RIGHT));

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    private Cell createCell(String text) {
        return new Cell().add(new Paragraph(text))
                .setPadding(5)
                .setBorder(new SolidBorder(0.5f));
    }

    private Cell createHeaderCell(String text) {
        return new Cell().add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setPadding(5)
                .setBorder(new SolidBorder(1));
    }

    private Cell createInfoCell(String text) {
        return new Cell().add(new Paragraph(text))
                .setPadding(5)
                .setBorder(new SolidBorder(0.5f));
    }

    private Cell createSectionHeaderCell(String text) {
        return new Cell().add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setPadding(7)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(new SolidBorder(1.0f));
    }

    private Cell createDetailCell(String text) {
        return new Cell().add(new Paragraph(text))
                .setPadding(5)
                .setBorder(new SolidBorder(0.5f));
    }

    private Cell createSummaryHeaderCell(String text) {
        return new Cell().add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.GRAY)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(6)
                .setBorder(new SolidBorder(1));
    }

    private Cell createSummaryHeaderCell(String text, int colspan) {
        return new Cell(1, colspan).add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.GRAY)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(6)
                .setBorder(new SolidBorder(1));
    }

    private Cell createSummaryCell(String text) {
        return new Cell().add(new Paragraph(text).setBold())
                .setPadding(6)
                .setBorder(new SolidBorder(1));
    }

    private String formatMoney(BigDecimal amount) {
        return String.format("%,.0f", amount);
    }
}