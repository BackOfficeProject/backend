package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.GradesDto;
import com.backoffice.backoffice.service.GradesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GradesController {

    private final GradesService gradesService;

    //직급 생성
    @PostMapping("/grades/join")
    public ResponseEntity<ApiResponse<GradesDto>> gradesSave(@RequestBody GradesDto gradesDto) {
        gradesService.gradesSave(gradesDto);
        return ResponseEntity.ok(ApiResponse.success(gradesDto));
    }
    //직급 수정
    @PutMapping("/grades/update")
    public ResponseEntity<ApiResponse<GradesDto>> rolesUpdate(@RequestBody GradesDto gradesDto) {
        gradesService.gradesUpdate(gradesDto);
        return ResponseEntity.ok(ApiResponse.success(gradesDto));
    }
    //직급 삭제
    @PostMapping("/grades/delete")
    public ResponseEntity<ApiResponse<GradesDto>> rolesDelete(@RequestBody GradesDto gradesDto) {
       gradesService.gradesDelete(gradesDto);
        return ResponseEntity.ok(ApiResponse.success(gradesDto));
    }
    //특정 직급 하나 조회
    @GetMapping("/grades/find")
    public ResponseEntity<ApiResponse<List<GradesDto>>> findRoles(@RequestParam String name) {
        List<GradesDto> result = gradesService.findGrades(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직급 목록 조회
    @GetMapping("grades/findall")
    public ResponseEntity<ApiResponse<List<GradesDto>>> findAllRoles() {
        List<GradesDto> result = gradesService.findAllGrades();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
