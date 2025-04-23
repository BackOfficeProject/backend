package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employeeGrades.*;
import com.backoffice.backoffice.dto.employees.EmployeesDto;
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
    public EmployeeGradesInsertResponseDto insertGrade(EmployeeGradesInsertDto employeeGradesInsertDto) {

        // 1. 직급 부여 전 기존 직급 여부 확인
        Integer currentGradeId = employeeGradesMapper.findCurrentGradeId(employeeGradesInsertDto.getEmployeesId());
        if (currentGradeId != null) {
            throw new IllegalStateException("이미 직급이 부여된 직원입니다. 직급을 변경하려면 기존 직급을 종료 하여 주십시오.");
        }
        // 2. 직급 부여 (DB insert)
        try {
            employeeGradesMapper.insertGrade(employeeGradesInsertDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("직급 부여 중 오류 발생");
        }
        // 3. 등급 정보 조회
        GradesDto grade = gradesService.findId(employeeGradesInsertDto.getGradeId());
        // 5. 응답 DTO 변환
        return EmployeeGradesDtoMapper.toResponseDto(
                employeeGradesInsertDto.getEmployeesId(),
                grade.getName(),
                employeeGradesInsertDto.getGrantedReason()
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
    public List<EmployeeGradesAllResponseDto> employAllGrades(Integer employeesId) {
        // 직급 이력 조회
        List<EmployeeGradesAllDto> result = employeeGradesMapper.employAllGrades(employeesId);

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
    public void endCurrentGrade(EmployeeGradesEndCurrentDto employeeGradesEndCurrentDto) {
        try {
            employeeGradesMapper.endCurrentGrade(employeeGradesEndCurrentDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //직급 변경
    @Transactional
    public EmployeeGradesChangeResponseDto changeEmployeeGrade(EmployeeGradesChangeDto employeeGradesChangeDto) {

        Integer employeeId = employeeGradesChangeDto.getEmployeesId();
        Integer requestGradeId = employeeGradesChangeDto.getGradeId();

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
        EmployeeGradesEndCurrentDto endDto = new EmployeeGradesEndCurrentDto();
        endDto.setEmployeesId(employeeId);
        employeeGradesMapper.endCurrentGrade(endDto);

        // 새로운 직급 부여
        EmployeeGradesInsertDto startDto = new EmployeeGradesInsertDto();
        startDto.setEmployeesId(employeeId);
        startDto.setGradeId(requestGradeId);
        startDto.setGrantedReason(employeeGradesChangeDto.getGrantedReason());
        employeeGradesMapper.insertGrade(startDto);

        // 새 직급 정보 조회
        GradesDto grade = gradesService.findId(requestGradeId);

        // 응답 DTO 생성
        return EmployeeGradesDtoMapper.toResponseDto(employeeGradesChangeDto, grade);
    }
}

