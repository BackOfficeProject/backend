package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleFindResponse;
import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleDeleteResponse;
import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleJoinResponse;

public class DepartmentRolesDtoMapper {

    public static DepartmentRoleJoinResponse toResponseJoinDto(String departmentName, String roleName) {
        return new DepartmentRoleJoinResponse(departmentName, roleName);
    }


    public static DepartmentRoleDeleteResponse toResponseDto(String departmentName, String roleName) {
        return new DepartmentRoleDeleteResponse(departmentName, roleName);
    }



    public static DepartmentRoleFindResponse toResponseDto(String roleName) {
        return new DepartmentRoleFindResponse(roleName);
    }
}