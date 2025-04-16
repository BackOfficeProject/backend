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

    //직원이 가지고 있는 권한 + 부서에 포함이 되어 있으니 부서가 가지고 있는 권한도 간접적으로 받는다.
    // 그럼으로 직원이 갖고있는 권한 + 부서에 간접적으로 포함된 권한
    @GetMapping("/emplrole/all")
    public ResponseEntity<ApiResponse<List<String>>> findAllRolesByEmployeeId(@RequestParam Integer employeeId) {
        List<String> result = employeeRolesService.findAllRolesByEmployeeId(employeeId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
