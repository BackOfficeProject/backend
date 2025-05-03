package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.departmentRoles.requestDto.DepartmentRoleJoinRequest;
import com.backoffice.backoffice.dto.roles.RolesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentRolesMapper {

    //부서에게 권한 부여
    void roleToDepartment(DepartmentRoleJoinRequest departmentRoleJoinRequest);

    //부서에게 부여된 권한 목록 조회
    List<String> findRolesByDepartmentId(Integer departmentId);

    List<RolesDto> findRolesName(Integer departmentId);

    //부서의 특정 역할 제거
    void removeRoleFromDepartment(Map<String, Object> params);

    RolesDto findRoleNameById(Integer roleId);
}
