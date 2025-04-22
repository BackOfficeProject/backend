package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employeeGrades.EmployeeGradesAllDto;
import com.backoffice.backoffice.dto.employeeGrades.EmployeeGradesDto;
import com.backoffice.backoffice.dto.employeeGrades.EmployeeGradesEndCurrentDto;
import com.backoffice.backoffice.dto.employeeGrades.EmployeeGradesInsertDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeGradesMapper {

    void insertGrade(EmployeeGradesInsertDto employeeGradesInsertDto);

    List<String> currentGrades(Integer employeesId);

    List<EmployeeGradesAllDto> employAllGrades(Integer employeesId);

    void endCurrentGrade(EmployeeGradesEndCurrentDto employeeGradesEndCurrentDto);


    Integer findCurrentGradeId(Integer employeeId);


}
