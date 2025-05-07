package com.backoffice.backoffice.scheduler;

import com.backoffice.backoffice.service.PaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayScheduler {

    private final PaysService paysService;


    @Scheduled(cron = "0 0 10 15 * ?")
    public void calculateMonthlySalary() {
        paysService.salary();
    }
}
