package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.EmployeeRolesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeRolesMapper {
    //직원에게 권한 부여
    void roleToEmployee(EmployeeRolesDto employeeRolesDto);

    //직원에게 부여된 권한 목록 조회
    List<String> findRolesByEmployeeId(Integer employeeId);

    //직원의 특정 역할 제거
    void removeRoleFromEmployee(Map<String, Object> params);
}
