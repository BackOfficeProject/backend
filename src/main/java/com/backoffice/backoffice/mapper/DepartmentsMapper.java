package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDeleteRequest;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsRegisterRequest;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsUpdateRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentsMapper {

    void departmentSave(DepartmentsRegisterRequest departmentsRegisterRequest);

    void departmentsUpdate(DepartmentsUpdateRequest departmentsUpdateRequest);

    void departmentsDelete(DepartmentsDeleteRequest departmentsDeleteRequest);

    List<DepartmentsDto> findByDepartments(String name);

    DepartmentsDto findName(Integer id);

    DepartmentsDto findId(Integer id);

    List<DepartmentsDto> findAll();
}
