package com.backoffice.backoffice.dto.employeeRoles.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRolesRegisterResponse {
    private Integer employeesId;
    private String roleId;

}
