package com.example.t1.final_work.service;

import com.example.t1.final_work.error_handling.exceptions.TransactionNotFoundException;
import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.model.Transaction;
import com.example.t1.final_work.model.TransactionStatus;
import com.example.t1.final_work.model.dto.transaction.TransactionCompleteRequest;
import com.example.t1.final_work.model.dto.transaction.TransactionCompleteResult;
import com.example.t1.final_work.model.dto.transaction.TransactionRequest;
import com.example.t1.final_work.repository.TransactionRepository;
import com.example.t1.final_work.service.limit.LimitChangeService;
import com.example.t1.final_work.service.limit.LimitSearchService;
import com.example.t1.final_work.service.limit.LimitValidator;
import com.example.t1.final_work.service.transaction.TransactionStartService;
import com.example.t1.final_work.service.transaction.TransactionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitSearchService limitSearchService;
    private final LimitChangeService limitChangeService;
    private final LimitValidator limitValidator;
    private final TransactionStartService transactionStartService;
    private final TransactionValidator transactionValidator;

    public Transaction start(TransactionRequest request) {
        transactionValidator.validateOnStart(request);
        Limit limit = limitSearchService.findByUserId(request.getUserId());
        limitValidator.validateOnLimitReserve(limit, request.getAmount());
        return transactionStartService.start(limit, request);
    }

    @Transactional
    public void complete(TransactionCompleteRequest request) {
        Transaction transaction = transactionRepository.findByTransactionId(request.getTransactionId())
                .orElseThrow(() -> new TransactionNotFoundException(request.getTransactionId()));

        transactionValidator.validateOnComplete(transaction);

        if (TransactionCompleteResult.ROLLBACKED.equals(request.getResult())) {
            transaction.setStatus(TransactionStatus.ROLLBACKED);
        } else {
            transaction.setStatus(TransactionStatus.COMPLETED);
            limitChangeService.decrease(
                    transaction.getLimit(),
                    transaction.getAmount()
            );
        }

        transactionRepository.save(transaction);
    }
}
