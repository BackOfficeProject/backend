package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.EmployeeGradesDto;
import com.backoffice.backoffice.mapper.EmployeeGradesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeGradesService {

    private final EmployeeGradesMapper employeeGradesMapper;

    //직급 부여
    @Transactional
    public void insertGrade(EmployeeGradesDto employeeGradesDto) {
        try {
            employeeGradesMapper.insertGrade(employeeGradesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //현재 직급 조회
    @Transactional
    public List<String> currentGrades(Integer employeesId) {
        return employeeGradesMapper.currentGrades(employeesId);
    }

    //직급 이력 조회
//특정 직원이 지금까지 어떤 직급을 가졌는지 전체 이력을 볼 수 있음
    @Transactional
    public List<EmployeeGradesDto> employAllGrades(Integer employeesId) {
        return employeeGradesMapper.employAllGrades(employeesId);
    }

    //직급 종료 처리
    @Transactional
    public void endCurrentGrade(EmployeeGradesDto employeeGradesDto) {
        try {
            employeeGradesMapper.endCurrentGrade(employeeGradesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //직급 변경
    @Transactional
    public void changeEmployeeGrade(EmployeeGradesDto employeeGradesDto) {

        Integer employeeId = employeeGradesDto.getEmployeesId();
        Integer requestGradeId = employeeGradesDto.getGradeId();

        Integer currentGradeId = employeeGradesMapper.findCurrentGradeId(employeeId);

        if (currentGradeId == null) {
            throw new IllegalStateException("현재 직급이 존재하지 않습니다. 직급 변경 전에 먼저 직급을 부여하세요.");
        }


        if (currentGradeId != null & currentGradeId.equals(requestGradeId)) {
            throw new IllegalArgumentException("현재 직급과 동일한 직급으로는 변경할 수 없습니다.");
        }

        //현재 직급 종료
        employeeGradesMapper.endCurrentGrade(employeeGradesDto);

        //새 직급 부여
        employeeGradesMapper.insertGrade(employeeGradesDto);
    }
}
