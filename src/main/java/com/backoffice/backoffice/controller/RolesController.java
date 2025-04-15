package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.RolesDto;
import com.backoffice.backoffice.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RolesController {

    private final RolesService rolesService;

    //권한 생성
    @PostMapping("/roles/join")
    public ResponseEntity<ApiResponse<RolesDto>> rolesSave(@RequestBody RolesDto rolesDto) {
        rolesService.rolesSave(rolesDto);
        return ResponseEntity.ok(ApiResponse.success(rolesDto));
    }

    //권한 업데이트
    @PutMapping("/roles/update")
    public ResponseEntity<ApiResponse<RolesDto>> rolesUpdate(@RequestBody RolesDto rolesDto) {
        rolesService.rolesUpdate(rolesDto);
        return ResponseEntity.ok(ApiResponse.success(rolesDto));
    }

    //권한 삭제
    @PostMapping("/roles/delete")
    public ResponseEntity<ApiResponse<RolesDto>> rolesDelete(@RequestBody RolesDto rolesDto) {
        rolesService.rolesDelete(rolesDto);
        return ResponseEntity.ok(ApiResponse.success(rolesDto));
    }

    //권한 검색
    @PostMapping("/roles/find")
    public ResponseEntity<ApiResponse<List<RolesDto>>> findRoles(@RequestBody RolesDto rolesDto) {
        List<RolesDto> result = rolesService.findRoles(rolesDto.getName());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //모든 권한 검색
    @GetMapping("roles/findall")
    public ResponseEntity<ApiResponse<List<RolesDto>>> findAllRoles() {
        List<RolesDto> result = rolesService.findAllRoles();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
