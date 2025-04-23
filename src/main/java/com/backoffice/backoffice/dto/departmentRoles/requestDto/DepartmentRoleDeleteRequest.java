package com.backoffice.backoffice.dto.departmentRoles.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRoleDeleteRequest {

    private Integer departmentId;
    private Integer roleId;
}
