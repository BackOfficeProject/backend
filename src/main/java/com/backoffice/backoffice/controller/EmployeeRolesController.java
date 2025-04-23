package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employeeRoles.*;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeeRolesDtoMapper;
import com.backoffice.backoffice.service.EmployeeRolesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employeeRoles")
public class EmployeeRolesController {

    private final EmployeeRolesService employeeRolesService;

    //직원에게 권한 부여
    @Operation(summary = "직원에게 권한부여", description = "직원에게 권한을 부여합니다.")
    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<EmployeeRolesJoinResponseDto>> roleToEmployee(@RequestBody EmployeeRolesJoinDto employeeRolesJoinDto) {
        // 서비스 계층에서 역할 부여 및 응답 DTO 생성 처리
        EmployeeRolesJoinResponseDto responseDto = employeeRolesService.roleToEmployee(employeeRolesJoinDto);
        // 응답 반환
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //직원에게 부여된 권한 목록 조회
    @Operation(summary = "직원에게 부여된 권한 목록 조회", description = "직원에게 부여된 권한 목록을 조회합니다.")
    @GetMapping("/{employeeId}/roles")
    public ResponseEntity<ApiResponse<List<String>>> findRolesByEmployeeId(@PathVariable Integer employeeId) {
        List<String> result = employeeRolesService.findRolesByEmployeeId(employeeId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직원의 특정 역할 제거
    @Operation(summary = "직원에게 부여된 특정 권한 제거", description = "직원에게 부여된 권한을 제거 합니다.")
    @DeleteMapping("/{employeeId}/roles/{roleId}")
    public ResponseEntity<ApiResponse<EmployeeRolesDeleteResponseDto>> removeRoleFromEmployee(
            @PathVariable Integer employeeId,
            @PathVariable Integer roleId) {

        EmployeeRolesDeleteResponseDto responseDto = employeeRolesService.removeRoleFromEmployee(employeeId, roleId);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //직원이 가지고 있는 권한 + 부서에 포함된 권한 조회
    @Operation(summary = "직원이 가지고있는 권한 + 해당직원이 포함된 부서 권한 조회", description = "직원이 갖고있는 권한과 해당 직원이 포함된 부서 권한을 조회합니다.")
    @GetMapping("/{employeeId}/roles/all")
    public ResponseEntity<ApiResponse<List<String>>> findAllRolesByEmployeeId(@PathVariable Integer employeeId) {
        List<String> result = employeeRolesService.findAllRolesByEmployeeId(employeeId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


}
