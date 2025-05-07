package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.pays.requestDto.PaySalaryRequest;
import com.backoffice.backoffice.dto.pays.requestDto.findSalaryRequest;
import com.backoffice.backoffice.dto.pays.responseDto.findSalaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaysMapper {


    void salary(PaySalaryRequest paySalaryRequest);

    List<findSalaryRequest> findSalaryByMonth(Map<String, Object> params);

    List<findSalaryRequest> findSalaryAll(Map<String, Object> params);

    String getName(Integer employeeId);
}
