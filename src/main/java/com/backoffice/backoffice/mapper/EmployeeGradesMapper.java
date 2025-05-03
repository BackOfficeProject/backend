package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesAllRequest;
import com.backoffice.backoffice.dto.employeeGrades.requestDto.EmployeeGradesRegisterRequest;
import com.backoffice.backoffice.dto.grades.GradesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeGradesMapper {

    void insertGrade(EmployeeGradesRegisterRequest employeeGradesRegisterRequest);

    List<String> currentGrades(Integer employeesId);

    List<EmployeeGradesAllRequest> employAllGrades(Integer employeesId);

    void endCurrentGrade(Integer employeesId);


    GradesDto findCurrentGradeInfo(Integer employeeId);

    Integer findCurrentGradeId(Integer employeeId);


}
