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

    //직원이 가지고 있는 권한 + 부서에 포함이 되어 있으니 부서가 가지고 있는 권한도 간접적으로 받는다.
    // 그럼으로 직원이 갖고있는 권한 + 부서에 간접적으로 포함된 권한
    List<String> findAllRolesByEmployeeId(Integer employeeId);
}
