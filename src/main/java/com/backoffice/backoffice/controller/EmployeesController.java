package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeesDtoMapper;
import com.backoffice.backoffice.service.DepartmentsService;
import com.backoffice.backoffice.service.EmployeesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    private final EmployeesService employeesService;


    //사원 회원가입
    @Operation(summary = "직원계정 생성", description = "직원 계정을 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeesJoinResponseDto>> employeesSave(@RequestBody EmployeesJoinDto employeesJoinDto) {
        // 직원 저장 및 응답 DTO 생성
        EmployeesJoinResponseDto responseDto = employeesService.employeesSave(employeesJoinDto);

        // 응답 반환
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //사원 정보 업데이트
    @Operation(summary = "직원 정보 업데이트", description = "직원 정보를 업데이트 합니다.")
    @PutMapping
    public ResponseEntity<ApiResponse<EmployeesUpdateResponseDto>> employeesUpdate(@RequestBody EmployeesUpdateDto employeesUpdateDto) {

        EmployeesUpdateResponseDto responseDto = employeesService.employeesUpdate(employeesUpdateDto);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //사원 삭제
    @Operation(summary = "직원 계정 삭제", description = "직원의 계정을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesDeleteDto>> deleteEmployee(@PathVariable Integer id) {
        EmployeesDeleteDto responseDto = employeesService.employeesDelete(new EmployeesDeleteDto(id));
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }


    //사원 찾기
    @GetMapping("/{id}")
    @Operation(summary = "직원 계정 검색", description = "직원의 계정을 검색합니다.")
    public ResponseEntity<ApiResponse<List<EmployeesFindDto>>> findEmployees(@PathVariable Integer id) {
        List<EmployeesFindDto> result = employeesService.findEmployees(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //사원 전체 출력
    @Operation(summary = "모든 직원 출력", description = "모든 직원을 출력합니다.")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EmployeesFindDto>>> findAllEmployees() {
        List<EmployeesFindDto> result = employeesService.findAllEmployees();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
