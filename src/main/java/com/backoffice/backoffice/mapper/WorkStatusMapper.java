package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.workStatus.WorkStatusDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkStatusMapper {

    Integer checkIn(Integer employeesId);

    Integer countTodayCheckIn(Integer employeesId);

    WorkStatusDto findTodayStatus(Integer employeesId);


    Integer updateStatusToLate(Integer id);

    Integer checkOut(Integer employeesId);

    Integer insertAbsent(Integer employeesId);

    Integer checkEarlyOut(Integer employeesId);
}
