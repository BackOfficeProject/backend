package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.EmployeesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    void employeesSave(EmployeesDto employeesDto);

    String findByEmail(String email);

    void employeesUpdate(EmployeesDto employeesDto);

    void employeesDelete(EmployeesDto employeesDto);

    List<EmployeesDto> findEmployees(Integer id);

    List<EmployeesDto> findAllEmployees();
}
