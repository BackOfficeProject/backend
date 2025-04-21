package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.departmentRoles.*;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.mapper.DepartmentRolesDtoMapper;
import com.backoffice.backoffice.service.DepartmentRolesService;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.EmployeeRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentRolesController {

    private final DepartmentRolesService departmentRolesService;
    private final EmployeeRolesService employeeRolesService;
    private final DepartmentsService departmentsService;
    //부서에게 권한 부여
    @PostMapping("/departrole/join")
    public ResponseEntity<ApiResponse<DepartmentRolesResponseJoinDto>> roleToDepartment(@RequestBody DepartmentRolesJoinDto departmentRolesJoinDto) {
        departmentRolesService.roleToDepartment(departmentRolesJoinDto);

        DepartmentRolesResponseJoinDto responseDto = DepartmentRolesDtoMapper.toResponseDto(
                departmentRolesJoinDto,
                departmentsService,
                departmentRolesService
        );

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //부서에게 부여된 권한 목록 조회
    @GetMapping("/departrole/find")
    public ResponseEntity<ApiResponse<List<DepartmentRolesFindResponseDto>>>findRolesByDepartmentId (@RequestParam("departmentId") Integer departmentId) {
        List<RolesDto> rolesDtoList = departmentRolesService.findRolesName(departmentId);

        // 역할 이름을 DepartmentRolesFindResponseDto로 래핑
        List<DepartmentRolesFindResponseDto> result = rolesDtoList.stream()
                .map(role -> new DepartmentRolesFindResponseDto(role.getName()))
                .collect(Collectors.toList());

        // 반환
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //부서의 특별 역할 제거
    @PostMapping("/departrole/delete")
    public ResponseEntity<ApiResponse<DepartmentRolesResponseDeleteDto>> removeRoleFromDepartment(@RequestBody DepartmentRolesDeleteDto departmentRolesDeleteDto) {
        departmentRolesService.removeRoleFromDepartment(departmentRolesDeleteDto.getDepartmentId(), departmentRolesDeleteDto.getRoleId());

        DepartmentRolesResponseDeleteDto responseDto = DepartmentRolesDtoMapper.toResponseDto(departmentRolesDeleteDto ,departmentsService, departmentRolesService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
}
