package com.example.t1.final_work.controller;

import com.example.t1.final_work.converter.TransactionConverter;
import com.example.t1.final_work.model.Transaction;
import com.example.t1.final_work.model.dto.transaction.TransactionCompleteRequest;
import com.example.t1.final_work.model.dto.transaction.TransactionDto;
import com.example.t1.final_work.model.dto.transaction.TransactionRequest;
import com.example.t1.final_work.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    @PostMapping("/start")
    public TransactionDto startTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.start(request);
        return transactionConverter.convert(transaction);
    }

    @PostMapping("/complete")
    public void completeTransaction(@RequestBody TransactionCompleteRequest request) {
        transactionService.complete(request);
    }
}
