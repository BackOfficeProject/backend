package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employeeRoles.*;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.mapper.EmployeeRolesMapper;
import com.backoffice.backoffice.mapper.dtoMapper.EmployeeRolesDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class EmployeeRolesService {

    private final EmployeeRolesMapper employeeRolesMapper;


    //직원에게 권한 부여
    @Transactional
    public EmployeeRolesJoinResponseDto roleToEmployee(EmployeeRolesJoinDto employeeRolesJoinDto) {
        try {
            // 역할 부여 처리
            employeeRolesMapper.roleToEmployee(employeeRolesJoinDto);

            // 역할 조회
            List<RolesDto> rolesDtoList = employeeRolesMapper.findRoleId(employeeRolesJoinDto.getEmployeesId());

            // 비어 있지 않으면 첫 번째 역할을 사용, 비어 있으면 "No Role Assigned"
            String roleName = rolesDtoList.isEmpty() ? "No Role Assigned" : rolesDtoList.get(0).getName();

            // 응답 DTO 생성
            return EmployeeRolesDtoMapper.toResponseDto(employeeRolesJoinDto.getEmployeesId(), roleName);

        } catch (DuplicateKeyException e) {
            // 중복된 역할이 부여된 경우 예외 처리
            System.out.println("중복된 역할이 존재합니다.");
            throw new IllegalStateException("이미 해당 직원에게 역할이 부여되었습니다.");
        } catch (Exception e) {
            // 예상치 못한 오류 처리
            e.printStackTrace();
            throw new RuntimeException("예상치 못한 오류가 발생했습니다.");
        }
    }
    //직원에게 부여된 권한 목록 조회
    @Transactional
    public List<String> findRolesByEmployeeId(Integer employeeId) {
        return employeeRolesMapper.findRolesByEmployeeId(employeeId);
    }
    //직원의 특정 역할 제거
    @Transactional
    public EmployeeRolesDeleteResponseDto removeRoleFromEmployee(Integer employeesId, Integer roleId) {
        try {
            // 삭제 대상 역할 조회 (삭제 전)
            List<RolesDto> rolesBefore = employeeRolesMapper.findRoleId(employeesId);
            String deletedRoleName = rolesBefore.stream()
                    .filter(r -> r.getId().equals(roleId))
                    .map(RolesDto::getName)
                    .findFirst()
                    .orElse("Unknown Role");

            // 삭제
            Map<String, Object> params = new HashMap<>();
            params.put("employeesId", employeesId);
            params.put("roleId", roleId);
            employeeRolesMapper.removeRoleFromEmployee(params);

            // 응답: 삭제된 역할 이름 반환
            return EmployeeRolesDtoMapper.toDeleteResponseDto(employeesId, deletedRoleName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("예상치 못한 오류가 발생했습니다.");
        }
    }



    //직원이 가지고 있는 권한 + 부서에 포함이 되어 있으니 부서가 가지고 있는 권한도 간접적으로 받는다.
    // 그럼으로 직원이 갖고있는 권한 + 부서에 간접적으로 포함된 권한
    @Transactional
    public List<String> findAllRolesByEmployeeId(Integer employeeId) {
        return employeeRolesMapper.findAllRolesByEmployeeId(employeeId);
    }


    @Transactional
    public List<RolesDto> findRoleId(Integer employeeId) {
        return employeeRolesMapper.findRoleId(employeeId);
    }


}
