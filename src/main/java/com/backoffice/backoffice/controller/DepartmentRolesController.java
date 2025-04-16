package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.DepartmentRolesDto;
import com.backoffice.backoffice.service.DepartmentRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentRolesController {

    private final DepartmentRolesService departmentRolesService;

    //부서에게 권한 부여
    @PostMapping("/departrole/join")
    public ResponseEntity<ApiResponse<DepartmentRolesDto>> roleToDepartment(@RequestBody DepartmentRolesDto departmentRolesDto) {
        departmentRolesService.roleToDepartment(departmentRolesDto);
        return ResponseEntity.ok(ApiResponse.success(departmentRolesDto));
    }

    //부서에게 부여된 권한 목록 조회
    @GetMapping("/departrole/find")
    public ResponseEntity<ApiResponse<List<String>>> findRolesByDepartmentId(@RequestParam("departmentId") Integer departmentId) {
        List<String> result = departmentRolesService.findRolesByDepartmentId(departmentId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //부서의 특별 역할 제거
    @PostMapping("/departrole/delete")
    public ResponseEntity<ApiResponse<DepartmentRolesDto>> removeRoleFromDepartment(@RequestBody DepartmentRolesDto departmentRolesDto) {
        departmentRolesService.removeRoleFromDepartment(departmentRolesDto.getDepartmentId(), departmentRolesDto.getRoleId());
        return ResponseEntity.ok(ApiResponse.success(departmentRolesDto));
    }
}
