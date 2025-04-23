package com.backoffice.backoffice.dto.departmentRoles.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRoleDeleteResponse {

    private String departmentId;
    private String roleId;
}
