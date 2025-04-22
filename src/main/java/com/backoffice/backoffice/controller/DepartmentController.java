package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.departments.DepartmentsDeleteDto;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.DepartmentsJoinDto;
import com.backoffice.backoffice.dto.departments.DepartmentsUpdateDto;
import com.backoffice.backoffice.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentController {

    private final DepartmentsService departmentsService;

//회원가입
    @PostMapping("/departments/join")
    public ResponseEntity<ApiResponse<DepartmentsJoinDto>> departmentSave(@RequestBody DepartmentsJoinDto departmentsJoinDto) {
        departmentsService.departmentSave(departmentsJoinDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsJoinDto));
    }

//부서 업데이트
    @PutMapping("/departments/update")
    public ResponseEntity<ApiResponse<DepartmentsUpdateDto>> departmentsUpdate(@RequestBody DepartmentsUpdateDto departmentsUpdateDto) {
        departmentsService.departmentsUpdate(departmentsUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsUpdateDto));
    }

//부서 삭제
    @PostMapping("/departments/delete")
    public ResponseEntity<ApiResponse<DepartmentsDeleteDto>> departmentsDelete(@RequestBody DepartmentsDeleteDto departmentsDeleteDto) {
        departmentsService.departmentsDelete(departmentsDeleteDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsDeleteDto));
    }

//부서 찾기
@GetMapping("/departments/find/departments")
public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findByDepartments(@RequestParam("name") String name) {
    List<DepartmentsDto> result = departmentsService.findByDepartments(name);
    return ResponseEntity.ok(ApiResponse.success(result));
}


    //모든 부서 리스트 출력
    @GetMapping("/departments/findall")
    public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findAll() {
        List<DepartmentsDto> result = departmentsService.findAll();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
