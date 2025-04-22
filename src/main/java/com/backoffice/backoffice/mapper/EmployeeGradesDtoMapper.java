package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employeeGrades.*;
import com.backoffice.backoffice.dto.grades.GradesDeleteResponseDto;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.service.GradesService;

public class EmployeeGradesDtoMapper {
    public static EmployeeGradesInsertResponseDto toResponseDto(EmployeeGradesInsertDto employeeGradesInsertDto, GradesDto dto, GradesService gradesService) {
        GradesDto gradesDto = (GradesDto) gradesService.findId(dto.getId());
        return new EmployeeGradesInsertResponseDto(
                employeeGradesInsertDto.getEmployeesId(),
                dto.getName(),
                employeeGradesInsertDto.getGrantedReason()
        );
    }


    public static EmployeeGradesAllResponseDto toResponseDto(EmployeeGradesAllDto employeeGradesAllDto, GradesDto dto, GradesService gradesService) {
        GradesDto gradesDto = (GradesDto) gradesService.findId(dto.getId());
        return new EmployeeGradesAllResponseDto(
                employeeGradesAllDto.getId(),
                employeeGradesAllDto.getEmployeesId(),
                dto.getName(),
                employeeGradesAllDto.getGrantedStartDate(),
                employeeGradesAllDto.getGrantedEndDate(),
                employeeGradesAllDto.getGrantedReason()
        );
    }

    public static EmployeeGradesChangeResponseDto toResponseDto(EmployeeGradesChangeDto employeeGradesChangeDto, GradesDto dto, GradesService gradesService) {
        GradesDto gradesDto = (GradesDto) gradesService.findId(dto.getId());
        return new EmployeeGradesChangeResponseDto(
                employeeGradesChangeDto.getEmployeesId(),
                dto.getName(),
                employeeGradesChangeDto.getGrantedReason()
        );
    }



}