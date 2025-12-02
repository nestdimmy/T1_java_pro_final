package com.example.t1.final_work.error_handling.exceptions;

import com.example.t1.final_work.error_handling.AbstractException;
import org.springframework.http.HttpStatus;

public class TransactionAlreadyExistsException extends AbstractException {

    private final String message;

    public TransactionAlreadyExistsException(String transactionId) {
        this.message = "Транзакция с id: " + transactionId + " уже существует";
    }

    @Override
    public String getCode() {
        return "CODE-7";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
