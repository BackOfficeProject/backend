package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.roles.requestDto.RolesDeleteRequest;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.dto.roles.requestDto.RolesRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesMapper {

    void rolesSave(RolesRegisterRequest rolesRegisterRequest);

    List<RolesDto> findRoles(String name);

    List<RolesDto> findAllRoles();

    void rolesUpdate(RolesDto rolesDto);

    void rolesDelete(RolesDeleteRequest rolesDeleteRequest);

    RolesDto findId(Integer id);
}
