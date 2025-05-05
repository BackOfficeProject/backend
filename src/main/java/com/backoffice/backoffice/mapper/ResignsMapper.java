package com.backoffice.backoffice.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ResignsMapper {

    void quit(Map<String, Object> params);

    boolean checkRehired(Integer employeeId);
}
