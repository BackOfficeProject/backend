package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.PhoneNumberService;

public class EmployeesDtoMapper {

    public static EmployeesJoinResponseDto toResponseDto(EmployeesJoinDto dto,DepartmentsService departmentsService) {
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findName(dto.getDepartmentId());

        return new EmployeesJoinResponseDto(
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentsDto.getName(),
                dto.isStatus() ? "재직 중" : "퇴직",
                PhoneNumberService.formatPhone(dto.getPhone())
        );
    }


    public static EmployeesUpdateResponseDto toResponseDto(EmployeesUpdateDto dto, DepartmentsService departmentsService) {
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findId(dto.getDepartmentId());


        return new EmployeesUpdateResponseDto(

                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                departmentsDto.getName(),
                PhoneNumberService.formatPhone(dto.getPhone()),
                dto.isStatus() ? "재직 중" : "퇴직"
        );
    }

    public static EmployeesFindDto toResponseDto(EmployeesDto dto, DepartmentsService departmentsService) {
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findName(dto.getDepartmentId());
        return new EmployeesFindDto(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                dto.getPassword(),
                departmentsDto.getName(),
                dto.isStatus() ? "재직 중" : "퇴직",
                PhoneNumberService.formatPhone(dto.getPhone()),
                dto.getHireDate()
        );
    }
}
