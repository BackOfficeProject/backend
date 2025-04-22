package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesDto;
import com.backoffice.backoffice.dto.departmentRoles.DepartmentRolesJoinDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.mapper.DepartmentRolesMapper;
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

    //부서에게 권한부여
    @Transactional
    public void roleToDepartment(DepartmentRolesJoinDto departmentRolesJoinDto) {
        try {
            departmentRolesMapper.roleToDepartment(departmentRolesJoinDto);
        } catch (DuplicateKeyException e) {
            // 중복 키 예외 발생 시 처리
            throw new RuntimeException("해당 부서에 이미 역할이 할당되어 있습니다.", e);
        } catch (Exception e) {
            throw new RuntimeException("예상치 못한 오류가 발생했습니다.", e);
        }
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
