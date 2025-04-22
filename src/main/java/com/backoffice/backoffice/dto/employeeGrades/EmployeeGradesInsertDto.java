package com.backoffice.backoffice.dto.employeeGrades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeGradesInsertDto {

    private Integer employeesId;
    private Integer gradeId;
    private String grantedReason;

}
