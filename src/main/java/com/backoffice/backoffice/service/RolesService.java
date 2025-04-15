package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.RolesDto;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.RolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesService {

    private final RolesMapper rolesMapper;

    //권한 생성
    @Transactional
    public void rolesSave(RolesDto rolesDto) {
        try {
            rolesMapper.rolesSave(rolesDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    //권한 업데이트
    @Transactional
    public void rolesUpdate(RolesDto rolesDto) {
        try {
            rolesMapper.rolesUpdate(rolesDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //권한 삭제
    @Transactional
    public void rolesDelete(RolesDto rolesDto) {
        try {
        rolesMapper.rolesDelete(rolesDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //권한 조회
    @Transactional
    public List<RolesDto> findRoles(String name) {
        return rolesMapper.findRoles(name);
    }

    //모든 권한 조회
    public List<RolesDto> findAllRoles() {
        return rolesMapper.findAllRoles();
    }

}
