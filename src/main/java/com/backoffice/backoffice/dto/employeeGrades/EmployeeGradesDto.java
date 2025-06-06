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
public class EmployeeGradesDto {
    private Integer id;
    private Integer employeesId;
    private Integer gradeId;
    private Timestamp grantedStartDate;
    private Timestamp grantedEndDate;
    private String grantedReason;

}
