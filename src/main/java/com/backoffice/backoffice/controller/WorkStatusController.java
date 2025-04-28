package com.backoffice.backoffice.controller;


import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusCheckInResponse;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusCheckOutResponse;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusInsertAbsentResponse;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.scheduler.AbsentScheduler;
import com.backoffice.backoffice.service.WorkStatusService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workStatus")
public class WorkStatusController {

    private final AbsentScheduler absentScheduler;
    private final WorkStatusService workStatusService;


    //출근
    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<WorkStatusCheckInResponse>> checkIn(HttpSession session) {
        Integer employeeId = (Integer) session.getAttribute("employeeId");

        if (employeeId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        WorkStatusCheckInResponse responseDto = workStatusService.checkIn(employeeId);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }


    //퇴근
    @PutMapping("/check-out")
    public ResponseEntity<ApiResponse<WorkStatusCheckOutResponse>> checkOut(HttpSession session) {
        Integer employeeId = (Integer) session.getAttribute("employeeId");

        if (employeeId == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }
        WorkStatusCheckOutResponse responseDto = workStatusService.checkOut(employeeId);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }


//    //수동 실행용
//    @PostMapping("/absent-scheduler")
//    public String runAbsentSchedulter() {
//        absentScheduler.checkAbsent();
//        return "Absent Scheduler 수동 실행 완료";
//    }
}
