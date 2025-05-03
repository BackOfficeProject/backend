package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.roles.responseDto.RolesDeleteResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;

public class RolesDtoMapper {

    public static RolesDeleteResponseDto toResponseDto(RolesDto dto) {
        return new RolesDeleteResponseDto(
        dto.getName());
    }
}
