package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.roles.RolesDeleteDto;
import com.backoffice.backoffice.dto.roles.RolesDeleteResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.dto.roles.RolesJoinDto;
import com.backoffice.backoffice.mapper.dtoMapper.RolesDtoMapper;
import com.backoffice.backoffice.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RolesController {

    private final RolesService rolesService;

    //권한 생성
    @Operation(summary = "권한 생성", description = "권한을 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<RolesJoinDto>> rolesSave(@RequestBody RolesJoinDto rolesJoinDto) {
        rolesService.rolesSave(rolesJoinDto);
        return ResponseEntity.ok(ApiResponse.success(rolesJoinDto));
    }

    //권한 업데이트
    @Operation(summary = "권한 업데이트", description = "권한을 업데이트 합니다.")
    @PutMapping
    public ResponseEntity<ApiResponse<RolesDto>> rolesUpdate(@RequestBody RolesDto rolesDto) {
        rolesService.rolesUpdate(rolesDto);
        return ResponseEntity.ok(ApiResponse.success(rolesDto));
    }

    //권한 삭제
    @Operation(summary = "권한 삭제", description = "권한을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RolesDeleteResponseDto>> rolesDelete(@PathVariable Integer id) {
        RolesDeleteResponseDto responseDto = rolesService.rolesDelete(new RolesDeleteDto(id));
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //권한 검색
    @Operation(summary = "권한 검색", description = "권한의 이름을 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RolesDto>>> searchRole(@RequestParam String name) {
        List<RolesDto> result = rolesService.findRoles(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //모든 권한 검색
    @Operation(summary = "모든 권한 검색", description = "모든 권한을 찾아볼 수 있습니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RolesDto>>> getAllRoles() {
        List<RolesDto> result = rolesService.findAllRoles();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
