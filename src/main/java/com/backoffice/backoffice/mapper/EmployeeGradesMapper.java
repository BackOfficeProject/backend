package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.EmployeeGradesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeGradesMapper {

    void insertGrade(EmployeeGradesDto employeeGradesDto);

    List<String> currentGrades(Integer employeesId);

    List<EmployeeGradesDto> employAllGrades(Integer employeesId);

    void endCurrentGrade(EmployeeGradesDto employeeGradesDto);


    Integer findCurrentGradeId(Integer employeeId);


}
