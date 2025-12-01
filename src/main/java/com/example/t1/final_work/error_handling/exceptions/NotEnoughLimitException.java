package com.example.t1.final_work.error_handling.exceptions;

import com.example.t1.final_work.error_handling.AbstractException;
import org.springframework.http.HttpStatus;

public class NotEnoughLimitException extends AbstractException {

    @Override
    public String getCode() {
        return "CODE-3";
    }

    @Override
    public String getMessage() {
        return "Недостаточно лимита для резервирования";
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
