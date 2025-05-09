package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.pays.requestDto.SalaryDetailRequest;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter; // PdfWriter 임포트
import com.itextpdf.layout.Document;  // Document 임포트
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PdfGenerationService {


    private static final String KOREAN_FONT_PATH = "classpath:/fonts/NanumGothic-Regular.ttf";

    private PdfFont koreanFont;

    public PdfGenerationService() {
        try {
            // 한글 폰트 로드
            koreanFont = PdfFontFactory.createFont(KOREAN_FONT_PATH);
        } catch (Exception e) {
            // 폰트 로딩 실패 시 에러 처리
            e.printStackTrace();
            System.err.println("한글 폰트 로딩 실패! PDF에 한글이 제대로 표시되지 않을 수 있습니다.");
            koreanFont = null;  // 실패 시 null로 설정
        }
    }

    // 급여 명세서를 PDF로 생성하는 메서드
    public byte[] generateSalarySlipPdf(String employeeName, List<SalaryDetailRequest> salaryDetails) throws Exception {
        // PDF 생성 및 출력 스트림 설정
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // PdfWriter 객체 생성 (ByteArrayOutputStream과 연결)
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Document 객체 생성 (PdfWriter를 전달)
        Document document = new Document(new PdfDocument(writer));
        // 문서의 기본 폰트를 로드된 한글 폰트로 설정
        if (koreanFont != null) {
            document.setFont(koreanFont);  // 한글 폰트 적용
        } else {
            System.err.println("한글 폰트가 적용되지 않았습니다. PDF 한글 깨짐에 주의하세요.");
        }

        // 제목 추가
        document.add(new Paragraph("급여 명세서")
                .setFontSize(24)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER));

        // 직원 이름 추가
        document.add(new Paragraph("직원 이름: " + employeeName)
                .setFontSize(14)
                .setTextAlignment(TextAlignment.LEFT));

        // 급여 내역 추가 (테이블 형식으로 추가)
        Table table = new Table(2);  // 2개의 컬럼
        table.setWidth(100); // 테이블 너비 설정
        table.addCell(createHeaderCell("항목"));
        table.addCell(createHeaderCell("금액"));

        // 급여 내역 항목을 테이블에 추가
        for (SalaryDetailRequest detail : salaryDetails) {
            table.addCell(createCell("보너스"));
            table.addCell(createCell(String.format("%,.2f", detail.getBonus())));

            table.addCell(createCell("공제"));
            table.addCell(createCell(String.format("%,.2f", detail.getDeductions())));

            table.addCell(createCell("최종 급여"));
            table.addCell(createCell(String.format("%,.2f", detail.getFinalPay())));

            // 급여 지급일 포맷 수정
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(detail.getPayDate());
            table.addCell(createCell("급여 지급일"));
            table.addCell(createCell(formattedDate));
        }

        // 테이블을 문서에 추가
        document.add(table);

        // 문서 닫기
        document.close();

        return byteArrayOutputStream.toByteArray();  // PDF byte 배열 반환
    }

    // 셀을 생성하는 메서드 (포맷, 정렬 등)
    private Cell createCell(String text) {
        return new Cell()
                .add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5);
    }

    // 헤더 셀을 생성하는 메서드 (굵은 글씨, 배경 색 등)
    private Cell createHeaderCell(String text) {
        return new Cell()
                .add(new Paragraph(text))
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(10)
                .setBackgroundColor(new DeviceRgb(230, 230, 230)); // 연한 회색 배경 색
    }
}
