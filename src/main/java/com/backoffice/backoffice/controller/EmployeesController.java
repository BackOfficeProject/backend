package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employees.*;
import com.backoffice.backoffice.mapper.EmployeesDtoMapper;
import com.backoffice.backoffice.service.DepartmentsService;
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
    private final DepartmentsService departmentsService;

    //사원 회원가입
    @PostMapping("/employees/join")
    public ResponseEntity<ApiResponse<EmployeesJoinResponseDto>> employeesSave(@RequestBody EmployeesJoinDto employeesJoinDto) {

        employeesService.employeesSave(employeesJoinDto);

        EmployeesJoinResponseDto responseDto = EmployeesDtoMapper.toResponseDto(employeesJoinDto, departmentsService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));

    }

    //사원 정보 업데이트
    @PutMapping("/employees/update")
    public ResponseEntity<ApiResponse<EmployeesUpdateResponseDto>> employeesUpdate(@RequestBody EmployeesUpdateDto employeesUpdateDto) {
        employeesService.employeesUpdate(employeesUpdateDto);

        EmployeesUpdateResponseDto responseDto = EmployeesDtoMapper.toResponseDto(employeesUpdateDto, departmentsService);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //사원 삭제
    @PostMapping("/employees/delete")
    public ResponseEntity<ApiResponse<EmployeesDeleteDto>> employeesDelete(@RequestBody EmployeesDeleteDto employeesDeleteDto) {
        employeesService.employeesDelete(employeesDeleteDto);
        return ResponseEntity.ok(ApiResponse.success(employeesDeleteDto));
    }

    //사원 찾기
    @GetMapping("/employees/findemployees")
    public ResponseEntity<ApiResponse<List<EmployeesFindDto>>> findEmployees(@RequestParam("id") Integer id) {
        List<EmployeesFindDto> result = employeesService.findEmployees(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //사원 전체 출력
    @GetMapping("/employees/findall")
    public ResponseEntity<ApiResponse<List<EmployeesFindDto>>> findAllEmployees() {
        List<EmployeesFindDto> result = employeesService.findAllEmployees();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
