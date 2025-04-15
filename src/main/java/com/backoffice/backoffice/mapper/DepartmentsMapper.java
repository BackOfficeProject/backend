package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.DepartmentsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentsMapper {

    void departmentSave(DepartmentsDto departmentsDto);

    void departmentsUpdate(DepartmentsDto departmentsDto);

    void departmentsDelete(DepartmentsDto departmentsDto);

    List<DepartmentsDto> findByDepartments(String name);

    List<DepartmentsDto> findAll();
}
