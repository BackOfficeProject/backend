package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesChangeRequest;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesRegisterRequest;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesAllResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesChangeResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesEndCurrentResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesRegisterResponse;
import com.backoffice.backoffice.service.EmployeeGradesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employeeGrades")
public class EmployeeGradesController {
    private final EmployeeGradesService employeeGradesService;


    //직급 부여
    @Operation(summary = "직급 생성", description = "직급을 생성합니다.")
    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<EmployeeGradesRegisterResponse>> insertGrade(@RequestBody EmployeeGradesRegisterRequest employeeGradesRegisterRequest) {
        // 직급 부여 처리
        EmployeeGradesRegisterResponse responseDto = employeeGradesService.insertGrade(employeeGradesRegisterRequest);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    //현재 직급 조회
    @Operation(summary = "현재 직급 조회", description = "현재 직급을 조회합니다.")
    @GetMapping("/{employeesId}/grades/current")
    public ResponseEntity<ApiResponse<List<String>>> currentGrades(@PathVariable Integer employeesId) {
        List<String> result = employeeGradesService.currentGrades(employeesId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //직급 이력 조회
//특정 직원이 지금까지 어떤 직급을 가졌는지 전체 이력을 볼 수 있음
    @Operation(summary = "직급 이력 조회", description = "특정 직원이 지금까지 어떤 직급을 가졌는지 전체 이력을 볼 수 있습니다.")
    @GetMapping("/{employeesId}/grades/history")
    public ResponseEntity<ApiResponse<List<EmployeeGradesAllResponse>>> employAllGrades(@PathVariable Integer employeesId) {
        List<EmployeeGradesAllResponse> responseList = employeeGradesService.employAllGrades(employeesId);
        return ResponseEntity.ok(ApiResponse.success(responseList));
    }

    //직급 종료
    @Operation(summary = "직급 종료", description = "직급을 종료합니다.")
    @PutMapping("/end/{employeeId}")//
    public ResponseEntity<ApiResponse<EmployeeGradesEndCurrentResponse>> endCurrentGrade(@PathVariable Integer employeeId) {
        EmployeeGradesEndCurrentResponse responseDto = employeeGradesService.endCurrentGrade(employeeId);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }


    //직급변경
    @Operation(summary = "직급변경(승진, 강등)", description = "직원의 직급을 변경합니다.")
    @PostMapping("/change")
    public ResponseEntity<ApiResponse<EmployeeGradesChangeResponse>> promoteEmployee(@RequestBody EmployeeGradesChangeRequest employeeGradesChangeRequest) {
        // 서비스 계층에서 직급 변경 처리
        EmployeeGradesChangeResponse responseDto = employeeGradesService.changeEmployeeGrade(employeeGradesChangeRequest);

        // 응답 반환
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
}
