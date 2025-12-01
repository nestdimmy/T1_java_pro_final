package com.example.t1.final_work.model.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCompleteRequest {
    private String transactionId;
    private TransactionCompleteResult result;
}
