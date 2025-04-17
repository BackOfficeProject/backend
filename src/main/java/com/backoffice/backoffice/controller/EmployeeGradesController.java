package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.EmployeeGradesDto;
import com.backoffice.backoffice.mapper.EmployeeGradesMapper;
import com.backoffice.backoffice.service.EmployeeGradesService;
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

    //직급 부여
    @PostMapping("/insert/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesDto>> insertGrade(@RequestBody EmployeeGradesDto employeeGradesDto) {
        employeeGradesService.insertGrade(employeeGradesDto);
        return ResponseEntity.ok(ApiResponse.success(employeeGradesDto));
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
    public ResponseEntity<ApiResponse<List<EmployeeGradesDto>>> employAllGrades(@RequestParam Integer employeesId) {
        List<EmployeeGradesDto> result = employeeGradesService.employAllGrades(employeesId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직급 종료
    @PutMapping("/end/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesDto>> endCurrentGrade(@RequestBody EmployeeGradesDto employeeGradesDto) {
        employeeGradesService.endCurrentGrade(employeeGradesDto);
        return ResponseEntity.ok(ApiResponse.success(employeeGradesDto));
    }


    //직급변경
    @PostMapping("/change/grade")//
    public ResponseEntity<ApiResponse<EmployeeGradesDto>> promoteEmployee(@RequestBody EmployeeGradesDto employeeGradesDto) {

        employeeGradesService.changeEmployeeGrade(employeeGradesDto);
        return ResponseEntity.ok(ApiResponse.success(employeeGradesDto));
    }
}
