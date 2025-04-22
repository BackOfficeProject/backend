package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.EmployeesDtoMapper;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {


    private final EmployeesMapper employeesMapper;
    private final PhoneNumberService phoneNumberService;
    private final DepartmentsService departmentsService;

    //사원 가입
    public void employeesSave(EmployeesJoinDto employeesJoinDto) {
        try {
            // 이메일을 찾은 후, 반환된 값이 null이면 새로운 이메일로 저장
            String existingMail = employeesMapper.findByEmail(employeesJoinDto.getEmail());

            // 이메일이 존재하지 않으면 저장
            if (existingMail == null) {
                employeesMapper.employeesSave(employeesJoinDto);
            } else {
                // 이메일이 존재하면 이미 존재하는 메일로 처리
            }
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //사원 업데이트
    @Transactional
    public void employeesUpdate(EmployeesUpdateDto employeesUpdateDto) {
        try {
            // 업데이트 처리
            employeesMapper.employeesUpdate(employeesUpdateDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //사원 삭제
    @Transactional
    public void employeesDelete(EmployeesDeleteDto employeesDeleteDto) {
        try {
            employeesMapper.employeesDelete(employeesDeleteDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //사원 찾기
    public List<EmployeesFindDto> findEmployees(Integer id) {
        List<EmployeesDto> employeesList = employeesMapper.findEmployees(id);

        // EmployeesDto → EmployeesFindDto 변환
        return employeesList.stream()
                .map(dto -> EmployeesDtoMapper.toResponseDto(dto, departmentsService))
                .collect(Collectors.toList());
    }


    //사원 전체 출력
    @Transactional
    public List<EmployeesFindDto> findAllEmployees() {
        List<EmployeesDto> employeeAllList = employeesMapper.findAllEmployees();

        return employeeAllList.stream()
                .map(dto -> EmployeesDtoMapper.toResponseDto(dto, departmentsService))
                .collect(Collectors.toList());
    }
}
