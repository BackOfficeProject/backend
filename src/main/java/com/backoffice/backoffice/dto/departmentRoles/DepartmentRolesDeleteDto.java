package com.backoffice.backoffice.dto.departmentRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRolesDeleteDto {

    private Integer departmentId;
    private Integer roleId;
}
