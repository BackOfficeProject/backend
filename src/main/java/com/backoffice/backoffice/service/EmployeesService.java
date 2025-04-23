package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.employees.*;
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
    public EmployeesJoinResponseDto employeesSave(EmployeesJoinDto employeesJoinDto) {
        // 1. 이메일 중복 확인
        String existingMail = employeesMapper.findByEmail(employeesJoinDto.getEmail());

        // 이메일이 존재하지 않으면 저장
        if (existingMail == null) {
            employeesMapper.employeesSave(employeesJoinDto);
        } else {
            throw new CommonExceptionHandler(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 2. 응답 DTO 생성
        // 부서 정보 조회
        DepartmentsDto department = departmentsService.findName(employeesJoinDto.getDepartmentId());

        // 응답 DTO 반환
        return EmployeesDtoMapper.toResponseDto(
                employeesJoinDto,
                department.getName(),
                PhoneNumberService.formatPhone(employeesJoinDto.getPhone())
        );
    }

    //사원 업데이트
    @Transactional
    public EmployeesUpdateResponseDto employeesUpdate(EmployeesUpdateDto employeesUpdateDto) {

            employeesMapper.employeesUpdate(employeesUpdateDto);

        DepartmentsDto department = departmentsService.findName(employeesUpdateDto.getDepartmentId());

        return EmployeesDtoMapper.toResponseDto(
                employeesUpdateDto,
                department.getName(),
                PhoneNumberService.formatPhone(employeesUpdateDto.getPhone())
        );
    }

    //사원 삭제
    @Transactional
    public EmployeesDeleteDto employeesDelete(EmployeesDeleteDto employeesDeleteDto) {
        try {
            employeesMapper.employeesDelete(employeesDeleteDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return employeesDeleteDto;
    }

    //사원 찾기
    public  List<EmployeesFindDto> findEmployees(Integer id) {
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
    public List<EmployeesFindDto> findAllEmployees() {
        List<EmployeesDto> employeeAllList = employeesMapper.findAllEmployees();

        return employeeAllList.stream()
                .map(dto -> {
                    String departmentName = departmentsService.findName(dto.getDepartmentId()).getName();
                    return EmployeesDtoMapper.toResponseDto(dto, departmentName);
                })
                .collect(Collectors.toList());
    }

}
