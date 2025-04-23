package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.util.PhoneNumberService;

public class EmployeesDtoMapper {

    public static EmployeesJoinResponseDto toResponseDto(EmployeesJoinDto dto, String departmentName, String formattedPhone) {
        return new EmployeesJoinResponseDto(
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentName,
                dto.isStatus() ? "재직 중" : "퇴직",
                formattedPhone
        );
    }


    public static EmployeesUpdateResponseDto toResponseDto(EmployeesUpdateDto dto, String departmentName, String formattedPhone) {
        return new EmployeesUpdateResponseDto(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                departmentName,
                formattedPhone,
                dto.isStatus() ? "재직 중" : "퇴직"
        );
    }

    public static EmployeesFindDto toResponseDto(EmployeesDto dto, String departmentName) {
        return new EmployeesFindDto(
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
