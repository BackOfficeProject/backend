package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.dto.pays.requestDto.PaySalaryRequest;
import com.backoffice.backoffice.dto.pays.requestDto.SalaryDetailRequest;
import com.backoffice.backoffice.dto.pays.requestDto.findSalaryRequest;
import com.backoffice.backoffice.dto.pays.responseDto.PaySalaryResponse;
import com.backoffice.backoffice.dto.pays.responseDto.findSalaryResponse;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import com.backoffice.backoffice.mapper.GradesMapper;
import com.backoffice.backoffice.mapper.PaysMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaysService {

    private final PaysMapper paysMapper;
    private final GradesMapper gradesMapper;
    private final EmployeesMapper employeesMapper;
    private final EmailService emailService;
    private final PdfGenerationService pdfGenerationService;

    //급여 정산
    public void salary() {
        // 직원 리스트 가져오기
        List<EmployeesDto> employeeIds = employeesMapper.findAllEmployees();

        // 직원별 급여 계산
        for (EmployeesDto employee : employeeIds) {
            try {
                // 직급에 맞는 기본급 가져오기
                GradesDto grade = gradesMapper.findSalary(employee.getId());

                EmployeesDto employeesDto = employeesMapper.findHireANdDepartmentName(employee.getId());

                if (grade == null) {
                    throw new IllegalStateException("직급 정보가 없습니다. 직원 ID: " + employee.getId());
                }


                String departmentName = employeesDto.getDepartmentName();
                String gradeName = grade.getName();

                // 기본급을 가져온 후 보너스, 공제 등을 반영하여 최종 급여 계산
                BigDecimal basePay = grade.getBasePay();


                Timestamp hireDate = Timestamp.valueOf(employeesDto.getHireDate().toLocalDateTime());

                int currentMonth = java.time.LocalDate.now().getMonthValue();
                BigDecimal bonus = BigDecimal.ZERO;

                if (currentMonth == 12) {
                    bonus = BigDecimal.valueOf(200000.00);
                }
                BigDecimal deductionPercentage = BigDecimal.valueOf(0.10);  // 10%
                BigDecimal deductions = basePay.multiply(deductionPercentage);

                // 최종 급여 계산
                BigDecimal finalPay = basePay.add(bonus).subtract(deductions);

                LocalDateTime payDate = LocalDateTime.now();


                // 급여 계산 후 PaySalaryRequest 객체 생성
                PaySalaryRequest paySalaryRequest = new PaySalaryRequest();
                paySalaryRequest.setEmployeeId(employee.getId());
                paySalaryRequest.setBonus(bonus);
                paySalaryRequest.setDeductions(deductions);
                paySalaryRequest.setFinalPay(finalPay);
                paySalaryRequest.setPayDate(Timestamp.valueOf(payDate));



                // 급여 데이터 DB에 저장
                paysMapper.salary(paySalaryRequest);

                // 이메일 주소 가져오기
//                String mail = paysMapper.getMail(employee.getId());

                List<String> mailList = paysMapper.getMail(employee.getId());
                String mail = null;
                if (!mailList.isEmpty()) {
                     mail = mailList.get(0);
                }
                List<SalaryDetailRequest> salaryDetails = new ArrayList<>();
                salaryDetails.add(new SalaryDetailRequest(bonus, deductions, finalPay, Timestamp.valueOf(payDate),gradeName, departmentName, basePay,hireDate));


                // 급여 명세서를 PDF로 생성
                byte[] pdfData = pdfGenerationService.generateSalarySlipPdf(employee.getName(), salaryDetails);

                // 메일 발송
                try {
                    if (mail != null && !mail.isEmpty()) {
                        emailService.sendSalaryTransferEmailWithPdf(mail, pdfData);
                    } else {
                        System.err.println("메일 주소가 없습니다. 직원 ID: " + employee.getId());
                    }
                } catch (MessagingException e) {
                    System.err.println("메일 발송 중 오류 발생: " + e.getMessage());
                    e.printStackTrace();  // 메일 발송 오류를 로깅
                }

                // 급여 계산 결과 반환 (선택 사항)
                PaySalaryResponse response = new PaySalaryResponse(employee.getId(), bonus, deductions, finalPay, Timestamp.valueOf(payDate));
                // 필요한 경우 response를 로깅하거나 다른 처리
            } catch (Exception e) {
                System.err.println("급여 계산 중 오류 발생: " + e.getMessage());
                e.printStackTrace();  // 상세 예외 메시지 출력
            }
        }
    }


    // 어느달에 급여를 얼마받았는지
    public findSalaryResponse findSalaryByMonth(Integer month, HttpSession session) {

        Integer employeeId = (Integer) session.getAttribute("employeeId");
        System.out.println("Employee ID from session: " + employeeId);

        if (employeeId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("month", month);

        String name = paysMapper.getName(employeeId);

        List<findSalaryRequest> salaryList = paysMapper.findSalaryByMonth(params);

        findSalaryRequest salary = salaryList.isEmpty() ? null : salaryList.get(0);

        return findSalaryResponse.builder()
                .id(salary.getId())
                .employeeId(employeeId)
                .bonus(salary.getBonus())
                .deductions(salary.getDeductions())
                .finalPay(salary.getFinalPay())
                .payDate(salary.getPayDate())
                .name(name)
                .build();
    }

    public List<findSalaryResponse> findSalaryAll(HttpSession session) {

        Integer employeeId = (Integer) session.getAttribute("employeeId");
        System.out.println("employeeId : " +employeeId);

        if (employeeId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);

        String name = paysMapper.getName(employeeId);

        List<findSalaryRequest> salaryList = paysMapper.findSalaryAll(params);

        List<findSalaryResponse> responseList = salaryList.stream()
                .map(salary -> findSalaryResponse.builder()
                        .id(salary.getId())
                        .employeeId(employeeId)
                        .bonus(salary.getBonus())
                        .deductions(salary.getDeductions())
                        .finalPay(salary.getFinalPay())
                        .payDate(salary.getPayDate())
                        .name(name)
                        .build())
                .collect(Collectors.toList());  // List로 수집

        return responseList;
    }




}

