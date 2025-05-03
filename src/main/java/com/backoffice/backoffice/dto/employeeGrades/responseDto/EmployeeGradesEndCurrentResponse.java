package com.backoffice.backoffice.dto.employeeGrades.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeGradesEndCurrentResponse {
    private String employeesId; //종료된 직급의 이름
}
