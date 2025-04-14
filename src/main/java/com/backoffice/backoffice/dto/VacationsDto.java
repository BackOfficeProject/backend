package com.backoffice.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacationsDto {
    private Integer id;
    private Integer employeeId;
    private String type;
    private Timestamp startDate;
    private Timestamp endDate;
    private String reason;
    private String status;
    private String approvedBy;
    private Timestamp appliedAt;
}
