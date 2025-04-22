package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departmentRoles.*;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.grades.GradesDeleteDto;
import com.backoffice.backoffice.dto.grades.GradesDeleteResponseDto;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.DepartmentRolesService;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.GradesService;

import java.util.List;

public class GradesDtoMapper {
    public static GradesDeleteResponseDto toResponseDto(GradesDto dto, GradesService gradesService) {
        GradesDto gradesDto = (GradesDto) gradesService.findId(dto.getId());
        return new GradesDeleteResponseDto(
                dto.getName()
        );
    }



}