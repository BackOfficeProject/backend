package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesDto;
import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesJoinDto;
import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesResponseDeleteDto;
import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesResponseJoinDto;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.mapper.DepartmentRolesMapper;
import com.backoffice.backoffice.mapper.dtoMapper.DepartmentRolesDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentRolesService {

    private final DepartmentRolesMapper departmentRolesMapper;
    private final DepartmentsService departmentsService;
    private final RolesService rolesService;


    //부서에게 권한부여
    @Transactional
    public DepartmentRolesResponseJoinDto roleToDepartment(DepartmentRolesJoinDto dto) {
        departmentRolesMapper.roleToDepartment(dto);

        DepartmentsDto department = departmentsService.findName(dto.getDepartmentId());
        String departmentName = department != null ? department.getName() : "No Department";

        RolesDto role = departmentRolesMapper.findRoleNameById(dto.getRoleId());
        String roleName = role != null ? role.getName() : "No Role";

        return DepartmentRolesDtoMapper.toResponseJoinDto(departmentName, roleName);
    }




    //부서에게 부여된 권한 목록 조회
    @Transactional
    public List<String> findRolesByDepartmentId(Integer departmentId) {
        return departmentRolesMapper.findRolesByDepartmentId(departmentId);
    }

    @Transactional
    public List<RolesDto> findRolesName(Integer departmentId) {
        return departmentRolesMapper.findRolesName(departmentId);
    }

    //부서의 특정 역할 제거
    @Transactional
    public DepartmentRolesResponseDeleteDto removeRoleFromDepartment(Integer departmentId, Integer roleId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("departmentId", departmentId);
            params.put("roleId", roleId);

            departmentRolesMapper.removeRoleFromDepartment(params);

            // 부서명, 역할명 조회
            DepartmentsDto department = departmentsService.findName(departmentId);
            RolesDto role = rolesService.findId(roleId);

            String departmentName = department != null ? department.getName() : "No Department";
            String roleName = role != null ? role.getName() : "No Role";

            return DepartmentRolesDtoMapper.toResponseDto(departmentName, roleName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("역할 제거 중 오류 발생");
        }
    }





}
