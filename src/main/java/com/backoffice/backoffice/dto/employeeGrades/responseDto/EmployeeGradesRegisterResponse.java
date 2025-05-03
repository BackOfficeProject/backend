package com.backoffice.backoffice.dto.employeeGrades.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeGradesRegisterResponse {

    private Integer employeesId;
    private String gradeId;
    private String grantedReason;

}
