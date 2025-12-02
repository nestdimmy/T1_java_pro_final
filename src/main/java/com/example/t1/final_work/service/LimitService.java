package com.example.t1.final_work.service;

import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.model.dto.limit.LimitFetchResult;
import com.example.t1.final_work.model.dto.limit.LimitUpdateRequest;
import com.example.t1.final_work.repository.TransactionRepository;
import com.example.t1.final_work.service.limit.LimitChangeService;
import com.example.t1.final_work.service.limit.LimitSearchService;
import com.example.t1.final_work.service.limit.LimitValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitSearchService limitSearchService;
    private final LimitChangeService limitChangeService;
    private final LimitValidator limitValidator;
    private final TransactionRepository transactionRepository;

    public LimitFetchResult findByUserId(String userId) {
        Limit limit = limitSearchService.findByUserId(userId);
        Optional<BigDecimal> reservedSum = transactionRepository
                .countReservedTransactionsSum(limit.getId());
        BigDecimal availableLimit = limit.getCurrent();
        if (reservedSum.isPresent()) {
            availableLimit = availableLimit.subtract(reservedSum.get());
        }

        return new LimitFetchResult(limit.getUserId(), availableLimit);
    }

    @Transactional
    public Limit changeLimit(String userId, LimitUpdateRequest request) {
        Limit limit = limitSearchService.findByUserId(userId);
        limitValidator.validateOnLimitChange(limit, request.getNewAmount());
        return limitChangeService.reset(limit, request.getNewAmount());
    }
}
