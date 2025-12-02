package com.example.t1.final_work.service.transaction;

import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.model.Transaction;
import com.example.t1.final_work.model.TransactionStatus;
import com.example.t1.final_work.model.dto.transaction.TransactionRequest;
import com.example.t1.final_work.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionStartService {

    private final TransactionRepository transactionRepository;

    public Transaction start(Limit limit, TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(request.getTransactionId());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.RESERVED);
        transaction.setAmount(request.getAmount());
        transaction.setLimit(limit);
        return transactionRepository.save(transaction);
    }

}
