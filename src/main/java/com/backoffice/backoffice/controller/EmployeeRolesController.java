package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.EmployeeRolesDto;
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
    public ResponseEntity<ApiResponse<EmployeeRolesDto>> roleToEmployee(@RequestBody EmployeeRolesDto employeeRolesDto) {
        employeeRolesService.roleToEmployee(employeeRolesDto);
        return ResponseEntity.ok(ApiResponse.success(employeeRolesDto));
    }

    //직원에게 부여된 권한 목록 조회
    @GetMapping("/emplrole/find")
    public ResponseEntity<ApiResponse<List<String>>> findRolesByEmployeeId(@RequestParam("employeesId") Integer employeesId) {
        List<String> result = employeeRolesService.findRolesByEmployeeId(employeesId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직원의 특정 역할 제거
    @PostMapping("/emplrole/delete")
    public ResponseEntity<ApiResponse<EmployeeRolesDto>> removeRoleFromEmployee(@RequestBody EmployeeRolesDto employeeRolesDto) {
        employeeRolesService.removeRoleFromEmployee(employeeRolesDto.getEmployeesId(), employeeRolesDto.getRoleId());
        return ResponseEntity.ok(ApiResponse.success(employeeRolesDto));
    }

}
