package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesFindRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesRegisterRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesUpdateRequest;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesRegisterResponse;
import com.backoffice.backoffice.dto.employees.responseDto.EmployeesUpdateResponse;
import com.backoffice.backoffice.util.PhoneNumberService;

public class EmployeesDtoMapper {

    public static EmployeesRegisterResponse toResponseDto(EmployeesRegisterRequest dto, String departmentName, String formattedPhone) {
        return new EmployeesRegisterResponse(
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentName,
                dto.isStatus() ? "재직 중" : "퇴직",
                formattedPhone
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
