package com.example.t1.final_work.error_handling;

import com.example.t1.final_work.model.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorDto> handleNotFound(AbstractException ex) {
        ErrorDto result = new ErrorDto(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(result, ex.status());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleNotFound(RuntimeException ex) {
        ErrorDto result = new ErrorDto("CODE-9999", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
