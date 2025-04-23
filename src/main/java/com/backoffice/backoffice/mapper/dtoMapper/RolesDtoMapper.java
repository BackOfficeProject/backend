package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.roles.RolesDeleteResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.RolesService;

public class RolesDtoMapper {

    public static RolesDeleteResponseDto toResponseDto(RolesDto dto) {
        return new RolesDeleteResponseDto(
        dto.getName());
    }
}
