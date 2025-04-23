package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesAllRequest;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesChangeRequest;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesAllResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesChangeResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesEndCurrentResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesRegisterResponse;
import com.backoffice.backoffice.dto.grades.GradesDto;

public class EmployeeGradesDtoMapper {
    public static EmployeeGradesRegisterResponse toResponseDto(Integer employeesId, String gradeName, String grantedReason) {
        return new EmployeeGradesRegisterResponse(
                employeesId,
                gradeName,
                grantedReason
        );
    }


    public static EmployeeGradesAllResponse toResponseDto(EmployeeGradesAllRequest employeeGradesAllRequest, GradesDto dto) {
        return new EmployeeGradesAllResponse(
                employeeGradesAllRequest.getId(),
                employeeGradesAllRequest.getEmployeesId(),
                dto.getName(),
                employeeGradesAllRequest.getGrantedStartDate(),
                employeeGradesAllRequest.getGrantedEndDate(),
                employeeGradesAllRequest.getGrantedReason()
        );
    }

    public static EmployeeGradesChangeResponse toResponseDto(EmployeeGradesChangeRequest employeeGradesChangeRequest, GradesDto dto) {
        return new EmployeeGradesChangeResponse(
                employeeGradesChangeRequest.getEmployeesId(),
                dto.getName(), // 이미 이름 있음
                employeeGradesChangeRequest.getGrantedReason()
        );
    }

    public static EmployeeGradesEndCurrentResponse toResponseDto(GradesDto dto) {
        return new EmployeeGradesEndCurrentResponse(
                dto.getName()
        );
    }


}