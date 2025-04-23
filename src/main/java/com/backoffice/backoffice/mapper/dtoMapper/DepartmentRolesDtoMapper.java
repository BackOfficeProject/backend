package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.departmentRoles.*;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.DepartmentRolesService;
import com.backoffice.backoffice.service.DepartmentsService;

import java.util.List;

public class DepartmentRolesDtoMapper {

    public static DepartmentRolesResponseJoinDto toResponseJoinDto(String departmentName, String roleName) {
        return new DepartmentRolesResponseJoinDto(departmentName, roleName);
    }


    public static DepartmentRolesResponseDeleteDto toResponseDto(String departmentName, String roleName) {
        return new DepartmentRolesResponseDeleteDto(departmentName, roleName);
    }



    public static DepartmentRolesFindResponseDto toResponseDto(String roleName) {
        return new DepartmentRolesFindResponseDto(roleName);
    }
}