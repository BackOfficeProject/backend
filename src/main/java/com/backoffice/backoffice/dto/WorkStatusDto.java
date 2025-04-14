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
public class WorkStatusDto {
    private Integer id;
    private Integer employeesId;
    private Timestamp date;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private String status;
}
