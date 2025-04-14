package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.EmployeesDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeesMapper {

    void save(EmployeesDto employeesDto);

    String findByEmail(String email);
}
