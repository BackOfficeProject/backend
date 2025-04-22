package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.roles.RolesDeleteDto;
import com.backoffice.backoffice.dto.roles.RolesDto;
import com.backoffice.backoffice.dto.roles.RolesJoinDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesMapper {

    void rolesSave(RolesJoinDto rolesJoinDto);

    List<RolesDto> findRoles(String name);

    List<RolesDto> findAllRoles();

    void rolesUpdate(RolesDto rolesDto);

    void rolesDelete(RolesDeleteDto rolesDeleteDto);

    RolesDto findId(Integer id);
}
