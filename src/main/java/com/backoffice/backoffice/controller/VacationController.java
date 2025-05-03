package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.vacations.VacationsDto;
import com.backoffice.backoffice.dto.vacations.requestDto.VacationsApplyLeaveRequest;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsApplyLeaveResponse;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsApprovalResponse;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsListResponse;
import com.backoffice.backoffice.service.VacationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vacation")
public class VacationController {

    private final VacationService vacationService;


    //휴가신청
    @Operation(summary = "휴가신청", description = "휴가를 신청합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<VacationsApplyLeaveResponse>> insertVacationRequest(@RequestBody VacationsApplyLeaveRequest vacationsApplyLeaveRequest) {

        VacationsApplyLeaveResponse responseDto = vacationService.insertVacationRequest(vacationsApplyLeaveRequest);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

//휴가 승인
@Operation(summary = "관리자 휴가 승인", description = "휴가권한을 가진 관리자가 휴가를 승인합니다.")
    @PutMapping("/approval/{id}")
    public ResponseEntity<ApiResponse<VacationsApprovalResponse>> approveLeave(@PathVariable Integer id) {
        VacationsApprovalResponse responseDto = vacationService.approveLeave(id);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
//휴가 취소
@Operation(summary = "휴가 취소", description = "휴가 신청을 취소합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> cancelVacationRequest(@PathVariable Integer id) {
        return vacationService.cancelVacationRequest(id);
    }

    //휴가 목록 조회
    @Operation(summary = "휴가 목록 조회", description = "해당 사원의 휴가 목록을 조회합니다.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<List<VacationsListResponse>>> vacationRecord(@PathVariable Integer employeeId) {
        List<VacationsListResponse> responseDto = vacationService.vacationRecord(employeeId);

        return ResponseEntity.ok(ApiResponse.success(responseDto));

    }
}
