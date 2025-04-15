package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.DepartmentsDto;
import com.backoffice.backoffice.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentController {

    private final DepartmentsService departmentsService;

//회원가입
    @PostMapping("/departments/join")
    public ResponseEntity<ApiResponse<DepartmentsDto>> departmentSave(@RequestBody DepartmentsDto departmentsDto) {
        departmentsService.departmentSave(departmentsDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsDto));
    }

//부서 업데이트
    @PutMapping("/departments/update")
    public ResponseEntity<ApiResponse<DepartmentsDto>> departmentsUpdate(@RequestBody DepartmentsDto departmentsDto) {
        departmentsService.departmentsUpdate(departmentsDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsDto));
    }

//부서 삭제
    @PostMapping("/departments/delete")
    public ResponseEntity<ApiResponse<DepartmentsDto>> departmentsDelete(@RequestBody DepartmentsDto departmentsDto) {
        departmentsService.departmentsDelete(departmentsDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsDto));
    }

//부서 찾기
    @PostMapping("/departments/findDepartments")
    public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findByDepartments(@RequestBody DepartmentsDto departmentsDto) {
        List<DepartmentsDto> result = departmentsService.findByDepartments(departmentsDto.getName());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

//모든 부서 리스트 출력
    @GetMapping("/departments/findall")
    public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findAll() {
        List<DepartmentsDto> result = departmentsService.findAll();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
