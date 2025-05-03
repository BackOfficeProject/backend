package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.vacations.VacationsDto;
import com.backoffice.backoffice.dto.vacations.requestDto.VacationsApplyLeaveRequest;
import com.backoffice.backoffice.dto.vacations.responseDto.VacationsListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VacationsMapper {

    // 휴가 신청
    Integer insertVacationRequest(Map<String, Object> vacationParams);

    // 휴가 승인
    Integer approveLeave(Integer id);

    // 휴가 승인자 찾기
    String vacationManager(Integer employeeId);

    // 특정 기간 동안 휴가를 신청했는지 여부 확인
    boolean isEmployeeOnLeave(Map<String, Object> params);


    //휴가 신청자 찾기
    VacationsDto getVacationRequestEmployeeId(Integer id);

//휴가 취소
    Integer cancelVacationRequest(Integer id);


    List<VacationsListResponse> vacationRecord(Integer employeeId);


}
