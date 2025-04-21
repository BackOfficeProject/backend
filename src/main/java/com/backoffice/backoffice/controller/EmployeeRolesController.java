package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employeeRoles.*;
import com.backoffice.backoffice.mapper.EmployeeRolesDtoMapper;
import com.backoffice.backoffice.mapper.EmployeesDtoMapper;
import com.backoffice.backoffice.service.EmployeeRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmployeeRolesController {

    private final EmployeeRolesService employeeRolesService;

    //직원에게 권한 부여
    @PostMapping("/emplrole/join")
    public ResponseEntity<ApiResponse<EmployeeRolesJoinResponseDto>> roleToEmployee(@RequestBody EmployeeRolesJoinDto employeeRolesJoinDto) {
        // EmployeeRolesService에서 EmployeeRolesJoinDto를 처리하여 EmployeeRolesDto 얻기
        employeeRolesService.roleToEmployee(employeeRolesJoinDto);

        // EmployeeRolesDto에서 EmployeeRolesResponseDto로 변환
        EmployeeRolesJoinResponseDto responseDto = EmployeeRolesDtoMapper.toResponseDto(employeeRolesJoinDto, employeeRolesService);

        // ApiResponse.success로 성공 응답 반환
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //직원에게 부여된 권한 목록 조회
    @GetMapping("/emplrole/find")
    public ResponseEntity<ApiResponse<List<String>>> findRolesByEmployeeId(@RequestParam("employeesId") Integer employeesId) {
        List<String> result = employeeRolesService.findRolesByEmployeeId(employeesId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직원의 특정 역할 제거
    @PostMapping("/emplrole/delete")
    public ResponseEntity<ApiResponse<EmployeeRolesDeleteResponseDto>> removeRoleFromEmployee(@RequestBody EmployeeRolesDeleteDto employeeRolesDeleteDto) {
        employeeRolesService.removeRoleFromEmployee(employeeRolesDeleteDto.getEmployeesId(), employeeRolesDeleteDto.getRoleId());

        EmployeeRolesDeleteResponseDto responseDto = EmployeeRolesDtoMapper.toResponseDto(employeeRolesDeleteDto, employeeRolesService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //직원이 가지고 있는 권한 + 부서에 포함이 되어 있으니 부서가 가지고 있는 권한도 간접적으로 받는다.
    // 그럼으로 직원이 갖고있는 권한 + 부서에 간접적으로 포함된 권한
    @GetMapping("/emplrole/all")
    public ResponseEntity<ApiResponse<List<String>>> findAllRolesByEmployeeId(@RequestParam Integer employeeId) {
        List<String> result = employeeRolesService.findAllRolesByEmployeeId(employeeId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
