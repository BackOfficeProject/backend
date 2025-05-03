package com.backoffice.backoffice.mapper.dtoMapper;

import com.backoffice.backoffice.dto.grades.responseDto.GradesDeleteResponse;
import com.backoffice.backoffice.dto.grades.GradesDto;

public class GradesDtoMapper {
    public static GradesDeleteResponse toResponseDto(GradesDto dto) {
        return new GradesDeleteResponse(
                dto.getName()
        );
    }
}