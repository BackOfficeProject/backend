package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesDeleteDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesDeleteResponseDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesJoinDto;
import com.backoffice.backoffice.dto.employeeRoles.EmployeeRolesJoinResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.EmployeeRolesService;

import java.util.List;

public class EmployeeRolesDtoMapper {

    public static EmployeeRolesJoinResponseDto toResponseDto(EmployeeRolesJoinDto dto, EmployeeRolesService employeeRolesService) {
        List<RolesDto> rolesDtoList = employeeRolesService.findRoleId(dto.getEmployeesId());

        // 리스트가 비어 있지 않으면 첫 번째 역할을 사용
        if (!rolesDtoList.isEmpty()) {
            RolesDto rolesDto = rolesDtoList.get(0);  // 첫 번째 요소 선택
            return new EmployeeRolesJoinResponseDto(
                    dto.getEmployeesId(),
                    rolesDto.getName()
            );
        }
        return new EmployeeRolesJoinResponseDto(dto.getEmployeesId(), "No Role Assigned");
    }

    public static EmployeeRolesDeleteResponseDto toResponseDto(EmployeeRolesDeleteDto dto, EmployeeRolesService employeeRolesService) {
        List<RolesDto> rolesDtoList = employeeRolesService.findRoleId(dto.getEmployeesId());

        // 리스트가 비어 있지 않으면 첫 번째 역할을 사용
        if (!rolesDtoList.isEmpty()) {
            RolesDto rolesDto = rolesDtoList.get(0);  // 첫 번째 요소 선택
            return new EmployeeRolesDeleteResponseDto(
                    dto.getEmployeesId(),
                    rolesDto.getName()
            );
        }
        return new EmployeeRolesDeleteResponseDto(dto.getEmployeesId(), "No Role Assigned");
    }
}