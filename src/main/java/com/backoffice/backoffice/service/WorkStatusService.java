package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.workStatus.WorkStatusDto;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusCheckInResponse;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusCheckOutResponse;
import com.backoffice.backoffice.dto.workStatus.responseDto.WorkStatusInsertAbsentResponse;
import com.backoffice.backoffice.mapper.WorkStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class WorkStatusService {

    private final WorkStatusMapper workStatusMapper;


    //출근
    @Transactional
    public WorkStatusCheckInResponse checkIn(Integer employeesId) {
        if (workStatusMapper.countTodayCheckIn(employeesId) > 0) {
            throw new IllegalStateException("이미 출근했습니다.");
        }
        workStatusMapper.checkIn(employeesId);

        WorkStatusDto dto = workStatusMapper.findTodayStatus(employeesId);

        LocalTime standardTime = LocalTime.of(9, 0);
        if (dto.getCheckInTime().toLocalDateTime().toLocalTime().isAfter(standardTime)) {
            workStatusMapper.updateStatusToLate(dto.getId()); // 상태를 '지각' 으로 변경
            dto.setStatus("지각");
        }

        return WorkStatusCheckInResponse.builder()
                .id(dto.getId())  // id 값은 dto에서 가져와야 한다면, dto.getId()로 가져오기
                .employeesId(employeesId)
                .date(dto.getDate())  // date 값도 dto에서 가져오기
                .checkInTime(dto.getCheckInTime())  // checkInTime
                .checkOutTime(dto.getCheckOutTime())  // checkOutTime
                .status(dto.getStatus())  // status
                .build();
    }


    //퇴근
    @Transactional
    public WorkStatusCheckOutResponse checkOut(Integer employeesId) {
        WorkStatusDto dto = workStatusMapper.findTodayStatus(employeesId);

        if (dto.getCheckOutTime() != null) {
            throw new IllegalStateException("이미 퇴근했습니다.");
        }

        LocalTime standardTime = LocalTime.of(18, 0); // 6시
        LocalTime checkOutTime = LocalTime.now();

        // 조퇴 처리
        if (checkOutTime.isBefore(standardTime)) {
            workStatusMapper.checkEarlyOut(employeesId); // 조퇴 상태 업데이트
            dto.setStatus("조퇴");
        } else {
            workStatusMapper.checkOut(employeesId); // 정상 퇴근 처리
            dto.setStatus("퇴근");
        }

        dto = workStatusMapper.findTodayStatus(employeesId);

        return WorkStatusCheckOutResponse.builder()
                .id(dto.getId())
                .employeesId(employeesId)
                .date(dto.getDate())
                .checkInTime(dto.getCheckInTime())
                .checkOutTime(dto.getCheckOutTime())
                .status(dto.getStatus())
                .build();
    }
    //결근
    @Transactional
    public WorkStatusInsertAbsentResponse insertAbsent(Integer employeesId) {
        workStatusMapper.insertAbsent(employeesId);

        WorkStatusDto dto = workStatusMapper.findTodayStatus(employeesId);

        return WorkStatusInsertAbsentResponse.builder()
                .id(dto.getId())
                .employeesId(dto.getEmployeesId())
                .date(dto.getDate())
                .checkInTime(dto.getCheckInTime())
                .checkOutTime(dto.getCheckOutTime())
                .status(dto.getStatus())
                .build();
    }





}
