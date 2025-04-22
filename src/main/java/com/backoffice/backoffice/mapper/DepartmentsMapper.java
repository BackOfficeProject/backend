package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departments.DepartmentsDeleteDto;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.DepartmentsJoinDto;
import com.backoffice.backoffice.dto.departments.DepartmentsUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentsMapper {

    void departmentSave(DepartmentsJoinDto departmentsJoinDto);

    void departmentsUpdate(DepartmentsUpdateDto departmentsUpdateDto);

    void departmentsDelete(DepartmentsDeleteDto departmentsDeleteDto);

    List<DepartmentsDto> findByDepartments(String name);

    DepartmentsDto findName(Integer id);

    DepartmentsDto findId(Integer id);

    List<DepartmentsDto> findAll();
}
