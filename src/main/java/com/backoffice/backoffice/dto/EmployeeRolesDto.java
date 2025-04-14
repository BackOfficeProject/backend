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
public class EmployeeRolesDto {
    private Integer employeesId;
    private Integer roleId;
    private Timestamp grantedAt;
}
