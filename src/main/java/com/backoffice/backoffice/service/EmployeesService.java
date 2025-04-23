package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDto;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesDeleteRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesFindRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesRegisterRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesUpdateRequest;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesRegisterResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesUpdateResponse;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeesDtoMapper;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import com.backoffice.backoffice.util.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesMapper employeesMapper;
    private final DepartmentsService departmentsService;

    //사원 가입
    @Transactional
    public EmployeesRegisterResponse employeesSave(EmployeesRegisterRequest employeesRegisterRequest) {
        // 1. 이메일 중복 확인
        String existingMail = employeesMapper.findByEmail(employeesRegisterRequest.getEmail());

        // 이메일이 존재하지 않으면 저장
        if (existingMail == null) {
            employeesMapper.employeesSave(employeesRegisterRequest);
        } else {
            throw new CommonExceptionHandler(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 2. 응답 DTO 생성
        // 부서 정보 조회
        DepartmentsDto department = departmentsService.findName(employeesRegisterRequest.getDepartmentId());

        // 응답 DTO 반환
        return EmployeesDtoMapper.toResponseDto(
                employeesRegisterRequest,
                department.getName(),
                PhoneNumberService.formatPhone(employeesRegisterRequest.getPhone())
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
        try {
            employeesMapper.employeesDelete(employeesDeleteRequest);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return employeesDeleteRequest;
    }

    //사원 찾기
    public  List<EmployeesFindRequest> findEmployees(Integer id) {
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
