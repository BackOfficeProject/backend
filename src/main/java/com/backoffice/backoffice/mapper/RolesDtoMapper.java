package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.dto.roles.RolesDeleteDto;
import com.backoffice.backoffice.dto.roles.RolesDeleteResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.PhoneNumberService;
import com.backoffice.backoffice.service.RolesService;

public class RolesDtoMapper {

    public static RolesDeleteResponseDto toResponseDto(RolesDto dto, RolesService rolesService) {
        RolesDto rolesDto = (RolesDto) rolesService.findId(dto.getId());

        return new RolesDeleteResponseDto(
        dto.getName());
    }


}
