package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDto;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.dto.employees.requestDto.*;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesLoginResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesRegisterResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesUpdateResponse;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeesDtoMapper;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import com.backoffice.backoffice.util.PhoneNumberService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesMapper employeesMapper;
    private final DepartmentsService departmentsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;


    //사원 가입
    @Transactional
    public EmployeesRegisterResponse employeesSave(EmployeesRegisterRequest employeesRegisterRequest) throws MessagingException {
        // 1. 이메일 중복 확인
        String existingMail = employeesMapper.findByEmail(employeesRegisterRequest.getEmail());

        String encoderPassword = passwordEncoder.encode(employeesRegisterRequest.getPassword());

        employeesRegisterRequest.setPassword(encoderPassword);

        // 이메일이 존재하지 않으면 저장
        if (existingMail == null) {

            employeesMapper.employeesSave(employeesRegisterRequest);
        } else {
            throw new CommonExceptionHandler(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 부서 정보 조회
        DepartmentsDto department = departmentsService.findName(employeesRegisterRequest.getDepartmentId());

        EmployeesDto employee = employeesMapper.findEmpl(employeesRegisterRequest.getId());

        String status = employee.isStatus() ? "재직 중" : "퇴직";

        emailService.sendEmailAndSaveAuthCode(employeesRegisterRequest.getEmail());

        // 응답 DTO 반환
        return EmployeesDtoMapper.toResponseDto(
                employeesRegisterRequest,
                department.getName(),
                PhoneNumberService.formatPhone(employeesRegisterRequest.getPhone()),
                status

        );

    }

    //로그인
    public EmployeesLoginResponse login(EmployeesLoginRequest employeesLoginRequest, HttpSession session) {
        EmployeesDto user = employeesMapper.findByEmailAndPassword(employeesLoginRequest.getEmail());

        if (user == null) {
            throw new IllegalStateException("해당 메일이 없습니다.");
        }

        if (!passwordEncoder.matches(employeesLoginRequest.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }

        DepartmentsDto department = departmentsService.findName(user.getDepartmentId());

        session.setAttribute("employeeId", user.getId());
        return EmployeesDtoMapper.toResponseDto(
                user,
                department.getName(),
                PhoneNumberService.formatPhone(user.getPhone())
        );
    }

    //사원 업데이트
    @Transactional
    public EmployeesUpdateResponse employeesUpdate(EmployeesUpdateRequest employeesUpdateRequest) {

        employeesMapper.employeesUpdate(employeesUpdateRequest);

        DepartmentsDto department = departmentsService.findName(employeesUpdateRequest.getDepartmentId());

        return EmployeesDtoMapper.toResponseDto(
                employeesUpdateRequest,
                department.getName(),
                PhoneNumberService.formatPhone(employeesUpdateRequest.getPhone())
        );
    }

    //사원 삭제
    @Transactional
    public EmployeesDeleteRequest employeesDelete(EmployeesDeleteRequest employeesDeleteRequest) {

            employeesMapper.employeesDelete(employeesDeleteRequest);

        return employeesDeleteRequest;
    }

    //사원 찾기
    public List<EmployeesFindRequest> findEmployees(Integer id) {
        List<EmployeesDto> employeesList = employeesMapper.findEmployees(id);

        // EmployeesDto → EmployeesFindDto 변환
        return employeesList.stream()
                .map(dto -> {
                    String departmentName = departmentsService.findName(dto.getDepartmentId()).getName();
                    return EmployeesDtoMapper.toResponseDto(dto, departmentName);
                })
                .collect(Collectors.toList());
    }

    //사원 전체 출력
    @Transactional
    public List<EmployeesFindRequest> findAllEmployees() {
        List<EmployeesDto> employeeAllList = employeesMapper.findAllEmployees();

        return employeeAllList.stream()
                .map(dto -> {
                    String departmentName = departmentsService.findName(dto.getDepartmentId()).getName();
                    return EmployeesDtoMapper.toResponseDto(dto, departmentName);
                })
                .collect(Collectors.toList());
    }

}
