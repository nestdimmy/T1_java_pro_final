package com.example.t1.final_work.error_handling.exceptions;

import com.example.t1.final_work.error_handling.AbstractException;
import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends AbstractException {

    private final String message;

    public TransactionNotFoundException(String transactionId) {
        this.message = "Транзакция с id: " + transactionId + " не найдена в системе";
    }

    @Override
    public String getCode() {
        return "CODE-2";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
