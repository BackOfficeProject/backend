package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.departments.DepartmentsDeleteDto;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.DepartmentsJoinDto;
import com.backoffice.backoffice.dto.departments.DepartmentsUpdateDto;
import com.backoffice.backoffice.service.DepartmentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentsService departmentsService;

    // 부서 생성
  @Operation(summary = "부서 생성", description = "부서를 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentsJoinDto>> departmentSave(@RequestBody DepartmentsJoinDto departmentsJoinDto) {
        departmentsService.departmentSave(departmentsJoinDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsJoinDto));
    }

    // 부서 수정
    @Operation(summary = "부서 수정", description = "부서를 수정합니다.")
    @PutMapping
    public ResponseEntity<ApiResponse<DepartmentsUpdateDto>> departmentsUpdate(
            @RequestBody DepartmentsUpdateDto departmentsUpdateDto
    ) {

        DepartmentsUpdateDto responseDto = departmentsService.departmentsUpdate(departmentsUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //부서 삭제
    @Operation(summary = "부서 삭제", description = "부서를 삭제합니다.")
    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<DepartmentsDeleteDto>> departmentsDelete(@PathVariable String name) {
        DepartmentsDeleteDto responseDto = departmentsService.departmentsDelete(name);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }



    //부서 찾기
    @Operation(summary = "부서 검색", description = "부서를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findByDepartments(@RequestParam("name") String name) {
        List<DepartmentsDto> result = departmentsService.findByDepartments(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


    //모든 부서 리스트 출력
    @Operation(summary = "모든 부서 출력", description = "모든 부서를 출력합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentsDto>>> findAll() {
        List<DepartmentsDto> result = departmentsService.findAll();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
