package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employeeGrades.*;
import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.dto.grades.GradesDto;

public class EmployeeGradesDtoMapper {
    public static EmployeeGradesInsertResponseDto toResponseDto(Integer employeesId, String gradeName, String grantedReason) {
        return new EmployeeGradesInsertResponseDto(
                employeesId,
                gradeName,
                grantedReason
        );
    }


    public static EmployeeGradesAllResponseDto toResponseDto(EmployeeGradesAllDto employeeGradesAllDto, GradesDto dto) {
        return new EmployeeGradesAllResponseDto(
                employeeGradesAllDto.getId(),
                employeeGradesAllDto.getEmployeesId(),
                dto.getName(),
                employeeGradesAllDto.getGrantedStartDate(),
                employeeGradesAllDto.getGrantedEndDate(),
                employeeGradesAllDto.getGrantedReason()
        );
    }

    public static EmployeeGradesChangeResponseDto toResponseDto(EmployeeGradesChangeDto employeeGradesChangeDto, GradesDto dto) {
        return new EmployeeGradesChangeResponseDto(
                employeeGradesChangeDto.getEmployeesId(),
                dto.getName(), // 이미 이름 있음
                employeeGradesChangeDto.getGrantedReason()
        );
    }


}