package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departmentRoles.*;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesJoinResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.DepartmentRolesService;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.EmployeeRolesService;

import java.util.List;

public class DepartmentRolesDtoMapper {

    public static DepartmentRolesResponseJoinDto toResponseDto(DepartmentRolesJoinDto dto, DepartmentsService departmentsService, DepartmentRolesService departmentRolesService) {
        // 부서 이름 조회
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findName(dto.getDepartmentId());

        // 부서 ID를 기반으로 역할 리스트 조회
        List<RolesDto> rolesDtoList = departmentRolesService.findRolesName(dto.getDepartmentId());

        // 역할 이름 선택 (없으면 기본값 처리)
        String roleName = rolesDtoList.isEmpty() ? "No Role Assigned" : rolesDtoList.get(0).getName();

        // 부서 이름
        String departmentName = departmentsDto != null ? departmentsDto.getName() : "No Department";

        return new DepartmentRolesResponseJoinDto(
                departmentName,
                roleName
        );
    }

    public static DepartmentRolesResponseDeleteDto toResponseDto(DepartmentRolesDeleteDto dto, DepartmentsService departmentsService, DepartmentRolesService departmentRolesService) {
        // 부서 이름 조회
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findName(dto.getDepartmentId());

        // 부서 ID를 기반으로 역할 리스트 조회
        List<RolesDto> rolesDtoList = departmentRolesService.findRolesName(dto.getDepartmentId());

        // 역할 이름 선택 (없으면 기본값 처리)
        String roleName = rolesDtoList.isEmpty() ? "No Role Assigned" : rolesDtoList.get(0).getName();

        // 부서 이름
        String departmentName = departmentsDto != null ? departmentsDto.getName() : "No Department";

        return new DepartmentRolesResponseDeleteDto(
                departmentName,
                roleName
        );
    }


    public static DepartmentRolesFindResponseDto toResponseDto(DepartmentRolesFindDto dto, DepartmentsService departmentsService, DepartmentRolesService departmentRolesService) {
        // 부서 이름 조회
        DepartmentsDto departmentsDto = (DepartmentsDto) departmentsService.findName(dto.getDepartmentId());

        // 부서 ID를 기반으로 역할 리스트 조회
        List<RolesDto> rolesDtoList = departmentRolesService.findRolesName(dto.getDepartmentId());

        // 역할 이름 선택 (없으면 기본값 처리)
        String roleName = rolesDtoList.isEmpty() ? "No Role Assigned" : rolesDtoList.get(0).getName();


        return new DepartmentRolesFindResponseDto(
                roleName
        );
    }
}