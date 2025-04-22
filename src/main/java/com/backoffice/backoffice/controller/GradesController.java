package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.grades.*;
import com.backoffice.backoffice.mapper.GradesDtoMapper;
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
    public ResponseEntity<ApiResponse<GradesJoinDto>> gradesSave(@RequestBody GradesJoinDto gradesJoinDto) {
        gradesService.gradesSave(gradesJoinDto);
        return ResponseEntity.ok(ApiResponse.success(gradesJoinDto));
    }
    //직급 수정
    @PutMapping("/grades/update")
    public ResponseEntity<ApiResponse<GradesUpdateDto>> rolesUpdate(@RequestBody GradesUpdateDto gradesUpdateDto) {
        gradesService.gradesUpdate(gradesUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(gradesUpdateDto));
    }
    //직급 삭제
    @PostMapping("/grades/delete")
    public ResponseEntity<ApiResponse<GradesDeleteResponseDto>> rolesDelete(@RequestBody GradesDeleteDto gradesDeleteDto) {
        GradesDto dto = gradesService.findId(gradesDeleteDto.getId());
       gradesService.gradesDelete(gradesDeleteDto);

        GradesDeleteResponseDto responseDto = GradesDtoMapper.toResponseDto(dto, gradesService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
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
