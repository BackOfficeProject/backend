package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employeeRoles.responseDto.EmployeeRolesDeleteResponse;
import com.backoffice.backoffice.dto.employeeRoles.responseDto.EmployeeRolesRegisterResponse;

public class EmployeeRolesDtoMapper {

    public static EmployeeRolesRegisterResponse toResponseDto(Integer employeeId, String roleName) {
        return new EmployeeRolesRegisterResponse(employeeId, roleName);
    }

    public static EmployeeRolesDeleteResponse toDeleteResponseDto(Integer employeeId, String roleName) {
        return new EmployeeRolesDeleteResponse(employeeId,roleName);
    }
}