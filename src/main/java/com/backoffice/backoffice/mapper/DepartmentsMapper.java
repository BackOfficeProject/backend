package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.DepartmentsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentsMapper {

    void departmentSave(DepartmentsDto departmentsDto);
}
