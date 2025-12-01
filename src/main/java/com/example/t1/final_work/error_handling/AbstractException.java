package com.example.t1.final_work.error_handling;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
    public abstract String getCode();
    public abstract String getMessage();
    public abstract HttpStatus status();
}
