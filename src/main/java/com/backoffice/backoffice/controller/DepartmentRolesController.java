package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.departmentRoles.requestDto.DepartmentRoleJoinRequest;
import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleFindResponse;
import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleDeleteResponse;
import com.backoffice.backoffice.dto.departmentRoles.responseDto.DepartmentRoleJoinResponse;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.service.DepartmentRolesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departmentRole")
public class DepartmentRolesController {

    private final DepartmentRolesService departmentRolesService;


    //부서에게 권한 부여
    @Operation(summary = "부서에 권한부여", description = "부서에 권한을 부여합니다.")
    @PostMapping("/roles")
    public ResponseEntity<ApiResponse<DepartmentRoleJoinResponse>> roleToDepartment(
            @RequestBody DepartmentRoleJoinRequest departmentRoleJoinRequest
    ) {
        DepartmentRoleJoinResponse responseDto = departmentRolesService.roleToDepartment(departmentRoleJoinRequest);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }


    //부서에게 부여된 권한 목록 조회
    @Operation(summary = "부서에 부여된 권한 목록 조회", description = "부서에 부여된 권한 목록을 조회합니다.")
    @GetMapping("/{departmentId}/roles")
    public ResponseEntity<ApiResponse<List<DepartmentRoleFindResponse>>> findRolesByDepartmentId(@PathVariable Integer departmentId) {
        List<RolesDto> rolesDtoList = departmentRolesService.findRolesName(departmentId);

        // 역할 이름을 DepartmentRolesFindResponseDto로 래핑
        List<DepartmentRoleFindResponse> result = rolesDtoList.stream()
                .map(role -> new DepartmentRoleFindResponse(role.getName()))
                .collect(Collectors.toList());

        // 반환
        return ResponseEntity.ok(ApiResponse.success(result));
    }


    //부서의 특별 역할 제거
    @Operation(summary = "부서에 부여된 권한 제거", description = "부서에 부여된 권한을 제거합니다.")
    @DeleteMapping("/{departmentId}/roles/{roleId}")
    public ResponseEntity<ApiResponse<DepartmentRoleDeleteResponse>> removeRoleFromDepartment(
            @PathVariable Integer departmentId,
            @PathVariable Integer roleId) {

        DepartmentRoleDeleteResponse responseDto =
                departmentRolesService.removeRoleFromDepartment(departmentId, roleId);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

}
