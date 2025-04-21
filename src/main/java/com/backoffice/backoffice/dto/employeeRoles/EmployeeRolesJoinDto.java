package com.backoffice.backoffice.dto.employeeRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRolesJoinDto {
    private Integer employeesId;
    private Integer roleId;
}
