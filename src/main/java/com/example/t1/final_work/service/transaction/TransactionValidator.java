package com.example.t1.final_work.service.transaction;

import com.example.t1.final_work.error_handling.exceptions.TransactionAlreadyCompletedException;
import com.example.t1.final_work.error_handling.exceptions.TransactionAlreadyExistsException;
import com.example.t1.final_work.model.Transaction;
import com.example.t1.final_work.model.TransactionStatus;
import com.example.t1.final_work.model.dto.transaction.TransactionRequest;
import com.example.t1.final_work.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionValidator {

    private final TransactionRepository transactionRepository;

    private static final Set<TransactionStatus> finalStatuses = Set.of(
            TransactionStatus.COMPLETED,
            TransactionStatus.ROLLBACKED
    );

    public void validateOnComplete(Transaction transaction) {
        if (finalStatuses.contains(transaction.getStatus())) {
            throw new TransactionAlreadyCompletedException(transaction.getTransactionId());
        }
    }

    public void validateOnStart(TransactionRequest request) {
        Optional<Transaction> byTransactionId = transactionRepository.findByTransactionId(request.getTransactionId());
        if (byTransactionId.isPresent()) {
            throw new TransactionAlreadyExistsException(request.getTransactionId());
        }
    }
}
