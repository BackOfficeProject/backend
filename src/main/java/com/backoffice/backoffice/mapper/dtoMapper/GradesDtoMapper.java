package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.grades.GradesDeleteResponseDto;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.service.GradesService;

public class GradesDtoMapper {
    public static GradesDeleteResponseDto toResponseDto(GradesDto dto) {
        return new GradesDeleteResponseDto(
                dto.getName()
        );
    }
}