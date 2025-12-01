package com.example.t1.final_work.service.limit;

import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitChangeService {

    private final LimitRepository limitRepository;

    @Value("${limits.default-amount}")
    private BigDecimal defaultAmount;


    public Limit reset(Limit limit, BigDecimal newAmount) {
        limit.setCurrent(newAmount);
        return limitRepository.save(limit);
    }

    public void decrease(Limit limit, BigDecimal decreaseAmount) {
        limitRepository.decreaseLimit(limit.getId(), decreaseAmount);
    }

    public void resetLimit() {
        limitRepository.resetLimitToDefault(defaultAmount);
    }
}
