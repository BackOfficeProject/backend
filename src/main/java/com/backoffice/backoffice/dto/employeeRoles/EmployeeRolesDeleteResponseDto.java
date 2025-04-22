package com.backoffice.backoffice.dto.employeeRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRolesDeleteResponseDto {
    private Integer employeesId;
    private String roleId;

}
