package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employees.EmployeesDeleteDto;
import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.dto.employees.EmployeesJoinDto;
import com.backoffice.backoffice.dto.employees.EmployeesUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    void employeesSave(EmployeesJoinDto employeesJoinDto);

    String findByEmail(String email);

    void employeesUpdate(EmployeesUpdateDto employeesUpdateDto);

    void employeesDelete(EmployeesDeleteDto employeesDeleteDto);

    List<EmployeesDto> findEmployees(Integer id);

    List<EmployeesDto> findAllEmployees();
}
