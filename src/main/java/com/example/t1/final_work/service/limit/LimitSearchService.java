package com.example.t1.final_work.service.limit;

import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitSearchService {

    private final LimitRepository limitRepository;

    @Value("${limits.default-amount}")
    private BigDecimal defaultAmount;

    public Limit findByUserId(String userId) {
        Optional<Limit> byUserId = limitRepository.findByUserId(userId);
        return byUserId.orElseGet(() -> create(userId, defaultAmount));
    }

    public Limit create(String userId, BigDecimal limitAmount) {
        Limit limit = new Limit();
        limit.setUserId(userId);
        limit.setCurrent(limitAmount);
        return limitRepository.save(limit);
    }
}
