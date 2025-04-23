package com.backoffice.backoffice.dto.employeeRoles.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRolesDeleteRequest {
    private Integer employeesId;
    private Integer roleId;
}
