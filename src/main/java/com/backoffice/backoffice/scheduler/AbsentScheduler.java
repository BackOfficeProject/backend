package com.backoffice.backoffice.scheduler;

import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import com.backoffice.backoffice.service.WorkStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AbsentScheduler {

    private final WorkStatusService workStatusService;
    private final EmployeesMapper employeesMapper;


    @Scheduled(cron = "0 0 0 * * *")
    public void checkAbsent() {
        List<EmployeesDto> allEmployeesIds = employeesMapper.findAllEmployees();

        for (EmployeesDto employee : allEmployeesIds) {
            Integer employeeId = employee.getId();
            workStatusService.insertAbsent(employeeId);
        }
        System.out.println("결근 스케줄러 실행 완료");
    }
}
