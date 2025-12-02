package com.example.t1.final_work.converter;

import com.example.t1.final_work.model.Transaction;
import com.example.t1.final_work.model.dto.transaction.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class TransactionConverter {

    public TransactionDto convert(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setStatus(transaction.getStatus());
        dto.setAmount(transaction.getAmount());
        dto.setCreatedAt(transaction.getCreatedAt());
        return dto;
    }
}
