package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.DepartmentRolesDto;
import com.backoffice.backoffice.mapper.DepartmentRolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentRolesService {

    private final DepartmentRolesMapper departmentRolesMapper;

    //부서에게 권한부여
    @Transactional
    public void roleToDepartment(DepartmentRolesDto departmentRolesDto) {
        try {
            departmentRolesMapper.roleToDepartment(departmentRolesDto);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //부서에게 부여된 권한 목록 조회
    @Transactional
    public List<String> findRolesByDepartmentId(Integer departmentId) {
        return departmentRolesMapper.findRolesByDepartmentId(departmentId);
    }

    //부서의 특정 역할 제거
    @Transactional
    public void removeRoleFromDepartment(Integer departmentId, Integer roleId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("departmentId", departmentId);
            params.put("roleId", roleId);

            departmentRolesMapper.removeRoleFromDepartment(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
