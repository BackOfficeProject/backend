package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employeeGrades.*;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.mapper.EmployeeGradesDtoMapper;
import com.backoffice.backoffice.mapper.EmployeeGradesMapper;
import com.backoffice.backoffice.service.EmployeeGradesService;
import com.backoffice.backoffice.service.GradesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmployeeGradesController {
    private final EmployeeGradesService employeeGradesService;
    private final EmployeeGradesMapper employeeGradesMapper;
    private final GradesService gradesService;

    //직급 부여
    @PostMapping("/insert/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesInsertResponseDto>> insertGrade(@RequestBody EmployeeGradesInsertDto employeeGradesInsertDto) {
        GradesDto dto = gradesService.findId(employeeGradesInsertDto.getGradeId());
        employeeGradesService.insertGrade(employeeGradesInsertDto);

        EmployeeGradesInsertResponseDto ResponseDto = EmployeeGradesDtoMapper.toResponseDto(employeeGradesInsertDto ,dto, gradesService);
        return ResponseEntity.ok(ApiResponse.success(ResponseDto));
    }

    //현재 직급 조회
    @GetMapping("/current/grade")//
    public ResponseEntity<ApiResponse<List<String>>> currentGrades(@RequestParam Integer employeesId) {
        List<String> result = employeeGradesService.currentGrades(employeesId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직급 이력 조회
//특정 직원이 지금까지 어떤 직급을 가졌는지 전체 이력을 볼 수 있음
    @GetMapping("/emplo/all/grade")//
    public ResponseEntity<ApiResponse<List<EmployeeGradesAllResponseDto>>> employAllGrades(@RequestParam Integer employeesId) {
        List<EmployeeGradesAllDto> result = employeeGradesService.employAllGrades(employeesId);

        // 각 직급 이력 DTO를 응답 DTO로 변환
        List<EmployeeGradesAllResponseDto> responseList = result.stream()
                .map(dto -> {
                    // 각 직급의 이름을 조회
                    GradesDto gradesDto = gradesService.findId(dto.getGradeId());

                    // 수정된 방식으로 메서드 호출
                    return EmployeeGradesDtoMapper.toResponseDto(dto, gradesDto, gradesService);
                })
                .toList();

        return ResponseEntity.ok(ApiResponse.success(responseList));
    }

    //직급 종료
    @PutMapping("/end/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesEndCurrentDto>> endCurrentGrade(@RequestBody EmployeeGradesEndCurrentDto employeeGradesEndCurrentDto) {
        employeeGradesService.endCurrentGrade(employeeGradesEndCurrentDto);
        return ResponseEntity.ok(ApiResponse.success(employeeGradesEndCurrentDto));
    }


//    //직급변경
    @PostMapping("/change/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesChangeResponseDto>> promoteEmployee(@RequestBody EmployeeGradesChangeDto employeeGradesChangeDto) {
        employeeGradesService.changeEmployeeGrade(employeeGradesChangeDto);
        GradesDto dto = gradesService.findId(employeeGradesChangeDto.getGradeId());

        EmployeeGradesChangeResponseDto responseDto = EmployeeGradesDtoMapper.toResponseDto(employeeGradesChangeDto, dto, gradesService);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
}
