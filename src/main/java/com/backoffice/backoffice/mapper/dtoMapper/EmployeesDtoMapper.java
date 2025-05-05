package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesFindRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesLoginRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesRegisterRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesUpdateRequest;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesLoginResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesRegisterResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesUpdateResponse;
import com.backoffice.backoffice.util.PhoneNumberService;

public class EmployeesDtoMapper {

    public static EmployeesRegisterResponse toResponseDto(EmployeesRegisterRequest dto, String departmentName, String formattedPhone, String status) {


        return new EmployeesRegisterResponse(
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentName,
                status,
                formattedPhone
        );
    }

    public static EmployeesLoginResponse toResponseDto(EmployeesDto dto, String departmentName, String formattedPhone) {
        return new EmployeesLoginResponse(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                departmentName,  // departmentId에 실제 부서 이름 넣기
                dto.isStatus() ? "재직 중" : "퇴직",
                formattedPhone,
                dto.getHireDate()
        );
    }


    public static EmployeesUpdateResponse toResponseDto(EmployeesUpdateRequest dto, String departmentName, String formattedPhone) {
        return new EmployeesUpdateResponse(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                departmentName,
                formattedPhone,
                dto.isStatus() ? "재직 중" : "퇴직"
        );
    }

    public static EmployeesFindRequest toResponseDto(EmployeesDto dto, String departmentName) {
        return new EmployeesFindRequest(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentName,
                dto.isStatus() ? "재직 중" : "퇴직",
                PhoneNumberService.formatPhone(dto.getPhone()),
                dto.getHireDate()
        );
    }
}
