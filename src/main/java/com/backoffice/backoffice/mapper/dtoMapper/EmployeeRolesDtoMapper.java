package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesDeleteDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesDeleteResponseDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesJoinDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesJoinResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.EmployeeRolesService;

import java.util.List;

public class EmployeeRolesDtoMapper {

    public static EmployeeRolesJoinResponseDto toResponseDto(Integer employeeId, String roleName) {
        return new EmployeeRolesJoinResponseDto(employeeId, roleName);
    }

    public static EmployeeRolesDeleteResponseDto toDeleteResponseDto(Integer employeeId, String roleName) {
        return new EmployeeRolesDeleteResponseDto(employeeId,roleName);
    }
}