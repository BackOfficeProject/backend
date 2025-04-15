package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.RolesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesMapper {

    void rolesSave(RolesDto rolesDto);

    List<RolesDto> findRoles(String name);

    List<RolesDto> findAllRoles();

    void rolesUpdate(RolesDto rolesDto);

    void rolesDelete(RolesDto rolesDto);
}
