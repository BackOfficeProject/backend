package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.EmployeesDto;
import com.backoffice.backoffice.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmployeesController {
    private final EmployeesService employeesService;

    //사원 회원가입
    @PostMapping("/employees/join")
    public ResponseEntity<String> employeesSave(@RequestBody EmployeesDto employeesDto) {
        try {
            employeesService.employeesSave(employeesDto);
            return ResponseEntity.ok("사원 가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다");
        }
    }

    //사원 정보 업데이트
    @PutMapping("/employees/update")
    public ResponseEntity<ApiResponse<EmployeesDto>> employeesUpdate(@RequestBody EmployeesDto employeesDto) {
        employeesService.employeesUpdate(employeesDto);
        return ResponseEntity.ok(ApiResponse.success(employeesDto));
    }

    //사원 삭제
    @PostMapping("/employees/delete")
    public ResponseEntity<ApiResponse<EmployeesDto>> employeesDelete(@RequestBody EmployeesDto employeesDto) {
        employeesService.employeesDelete(employeesDto);
        return ResponseEntity.ok(ApiResponse.success(employeesDto));
    }

    //사원 찾기
    @GetMapping("/employees/findemployees")
    public ResponseEntity<ApiResponse<List<EmployeesDto>>> findEmployees(@RequestParam("id") Integer id) {
        List<EmployeesDto> result = employeesService.findEmployees(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //사원 전체 출력
    @GetMapping("/employees/findall")
    public ResponseEntity<ApiResponse<List<EmployeesDto>>> findAllEmployees() {
        List<EmployeesDto> result = employeesService.findAllEmployees();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
