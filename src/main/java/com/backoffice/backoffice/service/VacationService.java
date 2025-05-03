package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.vacations.VacationsDto;
import com.backoffice.backoffice.dto.vacations.requestDto.VacationsApplyLeaveRequest;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsApplyLeaveResponse;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsApprovalResponse;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsListResponse;
import com.backoffice.backoffice.mapper.VacationsMapper;
import com.backoffice.backoffice.mapper.WorkStatusMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationsMapper vacationsMapper;
    private final WorkStatusMapper workStatusMapper;

    @Transactional
    public VacationsApplyLeaveResponse insertVacationRequest(VacationsApplyLeaveRequest vacationsApplyLeaveRequest) {
        Integer employeeId = vacationsApplyLeaveRequest.getEmployeeId();
        String type = vacationsApplyLeaveRequest.getType();
        Timestamp startDate = vacationsApplyLeaveRequest.getStartDate();
        Timestamp endDate = vacationsApplyLeaveRequest.getEndDate();
        String reason = vacationsApplyLeaveRequest.getReason();
        Timestamp appliedAt = vacationsApplyLeaveRequest.getAppliedAt();


        //중복 휴가 신청 체크
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        boolean isOnLeave = vacationsMapper.isEmployeeOnLeave(params);

        if (isOnLeave) {
            throw new IllegalStateException("해당 기간에 이미 휴가를 신청한 기록이 있습니다.");
        }

        // 권한 가진 직원 찾기 (휴가 승인자 자동 찾기)
        String approvedBy = vacationsMapper.vacationManager(employeeId);

        if (approvedBy == null) {
            throw new IllegalStateException("휴가 승인 권한이 없습니다.");
        }

        // 휴가 신청 기록을 Vacations 테이블에 추가 (Map으로 매핑)
        Map<String, Object> vacationParams = new HashMap<>();
        vacationParams.put("employeeId", employeeId);
        vacationParams.put("type", type);
        vacationParams.put("startDate", startDate);
        vacationParams.put("endDate", endDate);
        vacationParams.put("reason", reason);
        vacationParams.put("approvedBy", approvedBy);

        vacationsMapper.insertVacationRequest(vacationParams);

        Integer id = (Integer) vacationParams.get("id");

        System.out.println("reason" + reason);
        // 응답 객체 생성 및 반환
        return VacationsApplyLeaveResponse.builder()
                .id(id)  // 생성된 id 추가
                .employeeId(employeeId)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .reason(reason)
                .status("대기")  // 상태는 '대기'로 설정
                .approvedBy(approvedBy)
                .appliedAt(appliedAt)  // 신청 날짜 포함
                .build();
    }

    @Transactional
    public VacationsApprovalResponse approveLeave(Integer id) {

        VacationsDto vacationRequest = vacationsMapper.getVacationRequestEmployeeId(id);

        if (vacationRequest == null) {
            throw new IllegalStateException("해당 휴가 신청 기록이 존재하지 않습니다.");
        }

        // 권한 가진 직원 찾기 (휴가 승인자 자동 찾기)
        String approvedBy = vacationsMapper.vacationManager(vacationRequest.getEmployeeId());

        if (approvedBy == null) {
            throw new IllegalStateException("승인자가 유효하지 않습니다.");
        }

        // 5. 휴가 신청 기록의 상태를 "승인"으로 변경
        int updatedRows = vacationsMapper.approveLeave(id);

        // 6. 상태가 업데이트되지 않았다면 예외 처리 (예: 이미 승인된 상태일 경우)
        if (updatedRows == 0) {
            throw new IllegalStateException("휴가 신청 상태 변경에 실패했습니다.");
        }

        return VacationsApprovalResponse.builder()
                .approvedBy(approvedBy)
                .status("승인")
                .build();
    }

    //휴가 취소
    @Transactional
    public ResponseEntity<ApiResponse<String>> cancelVacationRequest(Integer id) {

        //휴가 신청 기록 가져오기
        VacationsDto vacationRequest = vacationsMapper.getVacationRequestEmployeeId(id);

        if (vacationRequest == null) {
            throw new IllegalStateException("해당 휴가 신청 기록이 존재하지 않습니다.");
        }
        if ("승인".equals(vacationRequest.getStatus())) {
            throw new IllegalStateException("이미 승인된 휴가는 취소할 수 없습니다.");
        }

        vacationsMapper.cancelVacationRequest(id);


        return ResponseEntity.ok(ApiResponse.success("휴가 신청이 취소되었습니다."));
    }


    //휴가목록 조회
    public List<VacationsListResponse> vacationRecord(Integer employeeId) {
         return vacationsMapper.vacationRecord(employeeId);
    }


}
