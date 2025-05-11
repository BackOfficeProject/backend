package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.employees.requestDto.EmployeesDeleteRequest;
import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesRegisterRequest;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    void employeesSave(EmployeesRegisterRequest employeesRegisterRequest);

    String findByEmail(String email);

    void employeesUpdate(EmployeesUpdateRequest employeesUpdateRequest);

    void employeesDelete(EmployeesDeleteRequest employeesDeleteRequest);

    List<EmployeesDto> findEmployees(Integer id);

    List<EmployeesDto> findAllEmployees();

    EmployeesDto findByEmailAndPassword(String email);

    void statusChange(Integer id);

    EmployeesDto findEmpl(Integer id);

    String findDepartmentName(Integer id);

    EmployeesDto findHireANdDepartmentName(@Param("id") Integer id);
}
