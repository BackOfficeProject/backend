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
public class DepartmentRolesDto {

    private Integer departmentId;
    private Integer roleId;
    private Timestamp assignedAt;

}
