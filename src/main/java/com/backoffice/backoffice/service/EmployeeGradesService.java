package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesAllRequest;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesChangeRequest;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesRegisterRequest;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesAllResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesChangeResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesEndCurrentResponse;
import com.backoffice.backoffice.dto.employeeGrades.responseDto.EmployeeGradesRegisterResponse;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.mapper.EmployeeGradesMapper;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeeGradesDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeGradesService {

    private final EmployeeGradesMapper employeeGradesMapper;
    private final GradesService gradesService;

    //직급 부여
    @Transactional
    public EmployeeGradesRegisterResponse insertGrade(EmployeeGradesRegisterRequest employeeGradesRegisterRequest) {

        // 1. 직급 부여 전 기존 직급 여부 확인
        Integer currentGradeId = employeeGradesMapper.findCurrentGradeId(employeeGradesRegisterRequest.getEmployeesId());
        if (currentGradeId != null) {
            throw new IllegalStateException("이미 직급이 부여된 직원입니다. 직급을 변경하려면 기존 직급을 종료 하여 주십시오.");
        }
        // 2. 직급 부여 (DB insert)
        try {
            employeeGradesMapper.insertGrade(employeeGradesRegisterRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("직급 부여 중 오류 발생");
        }
        // 3. 등급 정보 조회
        GradesDto grade = gradesService.findId(employeeGradesRegisterRequest.getGradeId());
        // 5. 응답 DTO 변환
        return EmployeeGradesDtoMapper.toResponseDto(
                employeeGradesRegisterRequest.getEmployeesId(),
                grade.getName(),
                employeeGradesRegisterRequest.getGrantedReason()
        );
    }


    //현재 직급 조회
    @Transactional
    public List<String> currentGrades(Integer employeesId) {
        return employeeGradesMapper.currentGrades(employeesId);
    }

    //직급 이력 조회
//특정 직원이 지금까지 어떤 직급을 가졌는지 전체 이력을 볼 수 있음
    @Transactional
    public List<EmployeeGradesAllResponse> employAllGrades(Integer employeesId) {
        // 직급 이력 조회
        List<EmployeeGradesAllRequest> result = employeeGradesMapper.employAllGrades(employeesId);

        // 각 직급 이력 DTO를 응답 DTO로 변환
        return result.stream()
                .map(dto -> {
                    // 각 직급의 이름을 조회
                    GradesDto gradesDto = gradesService.findId(dto.getGradeId());

                    // 응답 DTO로 변환
                    return EmployeeGradesDtoMapper.toResponseDto(dto, gradesDto);
                })
                .toList();
    }


    //직급 종료 처리
    @Transactional
    public EmployeeGradesEndCurrentResponse endCurrentGrade(Integer employeeId ) {

        GradesDto dto = employeeGradesMapper.findCurrentGradeInfo(employeeId);
        dto.getName();

        employeeGradesMapper.endCurrentGrade(employeeId);

        return EmployeeGradesDtoMapper.toResponseDto(dto);
    }

    //직급 변경
    @Transactional
    public EmployeeGradesChangeResponse changeEmployeeGrade(EmployeeGradesChangeRequest employeeGradesChangeRequest) {

        Integer employeeId = employeeGradesChangeRequest.getEmployeesId();
        Integer requestGradeId = employeeGradesChangeRequest.getGradeId();

        // 현재 직급 조회
        Integer currentGradeId = employeeGradesMapper.findCurrentGradeId(employeeId);

        if (currentGradeId == null) {
            throw new IllegalStateException("현재 직급이 존재하지 않습니다. 직급 변경 전에 먼저 직급을 부여하세요");
        }

        // 동일 직급으로 변경하려는 경우 예외 처리
        if (currentGradeId != null && currentGradeId.equals(requestGradeId)) {
            throw new IllegalArgumentException("현재 지급과 동일한 직급으로 변경할 수 없습니다");
        }

        // 기존 직급 종료 처리
        employeeGradesMapper.endCurrentGrade(employeeId);

        // 새로운 직급 부여
        EmployeeGradesRegisterRequest startDto = new EmployeeGradesRegisterRequest();
        startDto.setEmployeesId(employeeId);
        startDto.setGradeId(requestGradeId);
        startDto.setGrantedReason(employeeGradesChangeRequest.getGrantedReason());
        employeeGradesMapper.insertGrade(startDto);

        // 새 직급 정보 조회
        GradesDto grade = gradesService.findId(requestGradeId);

        // 응답 DTO 생성
        return EmployeeGradesDtoMapper.toResponseDto(employeeGradesChangeRequest, grade);
    }
}

