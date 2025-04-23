package com.backoffice.backoffice.dto.employeeGrades.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeGradesChangeRequest {

    private Integer employeesId;
    private Integer gradeId;
    private String grantedReason;

}
