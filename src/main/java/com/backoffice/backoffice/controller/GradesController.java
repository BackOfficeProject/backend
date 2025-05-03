package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.grades.*;
import com.backoffice.backoffice.dto.grades.requestDto.GradesDeleteRequest;
import com.backoffice.backoffice.dto.grades.requestDto.GradesRegisterRequest;
import com.backoffice.backoffice.dto.grades.requestDto.GradesUpdateRequest;
import com.backoffice.backoffice.dto.grades.responseDto.GradesDeleteResponse;
import com.backoffice.backoffice.service.GradesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grades")
public class GradesController {

    private final GradesService gradesService;

    //직급 생성
    @PostMapping
    @Operation(summary = "직급 생성", description = "직급을 생성합니다.")
    public ResponseEntity<ApiResponse<GradesRegisterRequest>> gradesSave(@RequestBody GradesRegisterRequest gradesRegisterRequest) {
        gradesService.gradesSave(gradesRegisterRequest);
        return ResponseEntity.ok(ApiResponse.success(gradesRegisterRequest));
    }
    //직급 수정
    @Operation(summary = "직급 수정", description = "직급을 수정합니다.")
    @PutMapping
    public ResponseEntity<ApiResponse<GradesUpdateRequest>> updateGrade(@RequestBody GradesUpdateRequest gradesUpdateRequest) {
        gradesService.gradesUpdate(gradesUpdateRequest);
        return ResponseEntity.ok(ApiResponse.success(gradesUpdateRequest));
    }
    //직급 삭제
    @Operation(summary = "직급을 삭제", description = "직급을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<GradesDeleteResponse>> deleteGrade(@PathVariable Integer id) {
        GradesDeleteResponse responseDto = gradesService.gradesDelete(new GradesDeleteRequest(id));
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
    //특정 직급 하나 조회
    //자원을 조회할 때 조건을 필터링 하는 방식이므로 @RequestParam이 바람직하다.
    @Operation(summary = "특정 직급 조회", description = "직급을 조회합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<GradesDto>>> findGradesByName(@RequestParam("name") String name) {
        List<GradesDto> result = gradesService.findGrades(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직급 목록 조회
    @Operation(summary = "직급 목록 조회", description = "직급의 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<GradesDto>>> findAllRoles() {
        List<GradesDto> result = gradesService.findAllGrades();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
