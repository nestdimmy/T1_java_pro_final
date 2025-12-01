package com.example.t1.final_work.service.schedule;

import com.example.t1.final_work.service.limit.LimitChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetLimitScheduler {

    private final LimitChangeService limitChangeService;

    @Scheduled(cron = "${limits.reset.cron}")
    public void resetLimitScheduler() {
        limitChangeService.resetLimit();
    }
}
