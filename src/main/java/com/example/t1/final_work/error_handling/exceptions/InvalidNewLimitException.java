package com.example.t1.final_work.error_handling.exceptions;

import com.example.t1.final_work.error_handling.AbstractException;
import org.springframework.http.HttpStatus;

public class InvalidNewLimitException extends AbstractException {

    @Override
    public String getCode() {
        return "CODE-1";
    }

    @Override
    public String getMessage() {
        return "Новое значение лимита меньше, чем зарезервированная сумма вычетов";
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
