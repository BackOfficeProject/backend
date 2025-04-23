package com.backoffice.backoffice.dto.employeeGrades.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeGradesAllResponse {
    private Integer id;
    private Integer employeesId;
    private String gradeId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp grantedStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp grantedEndDate;

    private String grantedReason;

}
