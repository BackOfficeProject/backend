package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.roles.RolesDeleteDto;
import com.backoffice.backoffice.dto.roles.RolesDeleteResponseDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.dto.roles.RolesJoinDto;
import com.backoffice.backoffice.mapper.RolesDtoMapper;
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
    public ResponseEntity<ApiResponse<RolesJoinDto>> rolesSave(@RequestBody RolesJoinDto rolesJoinDto) {
        rolesService.rolesSave(rolesJoinDto);
        return ResponseEntity.ok(ApiResponse.success(rolesJoinDto));
    }

    //권한 업데이트
    @PutMapping("/roles/update")
    public ResponseEntity<ApiResponse<RolesDto>> rolesUpdate(@RequestBody RolesDto rolesDto) {
        rolesService.rolesUpdate(rolesDto);
        return ResponseEntity.ok(ApiResponse.success(rolesDto));
    }

    //권한 삭제
    @PostMapping("/roles/delete")
    public ResponseEntity<ApiResponse<RolesDeleteResponseDto>> rolesDelete(@RequestBody RolesDeleteDto rolesDeleteDto) {
        RolesDto dto = rolesService.findId(rolesDeleteDto.getId());
        rolesService.rolesDelete(rolesDeleteDto);

        RolesDeleteResponseDto responseDto = RolesDtoMapper.toResponseDto(dto,rolesService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //권한 검색
    @GetMapping("/roles/find")
    public ResponseEntity<ApiResponse<List<RolesDto>>> findRoles(@RequestParam String name) {
        List<RolesDto> result = rolesService.findRoles(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //모든 권한 검색
    @GetMapping("roles/findall")
    public ResponseEntity<ApiResponse<List<RolesDto>>> findAllRoles() {
        List<RolesDto> result = rolesService.findAllRoles();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
