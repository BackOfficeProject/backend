package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.EmployeeRolesDto;
import com.backoffice.backoffice.mapper.EmployeeRolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeRolesService {

    private final EmployeeRolesMapper employeeRolesMapper;

    //직원에게 권한 부여
    @Transactional
    public void roleToEmployee(EmployeeRolesDto employeeRolesDto) {
        try {
            employeeRolesMapper.roleToEmployee(employeeRolesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //직원에게 부여된 권한 목록 조회
    @Transactional
    public List<String> findRolesByEmployeeId(Integer employeeId) {
        return employeeRolesMapper.findRolesByEmployeeId(employeeId);
    }
    //직원의 특정 역할 제거
    @Transactional
    public void removeRoleFromEmployee(Integer employeesId, Integer roleId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("employeesId", employeesId);
            params.put("roleId", roleId);

            employeeRolesMapper.removeRoleFromEmployee(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //직원이 가지고 있는 권한 + 부서에 포함이 되어 있으니 부서가 가지고 있는 권한도 간접적으로 받는다.
    // 그럼으로 직원이 갖고있는 권한 + 부서에 간접적으로 포함된 권한
    @Transactional
    public List<String> findAllRolesByEmployeeId(Integer employeeId) {
        return employeeRolesMapper.findAllRolesByEmployeeId(employeeId);
    }


}
