package com.example.springsecurity.exception;

import com.example.springsecurity.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerAllException(Exception e) {
        return new ResponseDTO(e.getLocalizedMessage(), 400);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerRuntimeException(RuntimeException e) {
        return new ResponseDTO(e.getMessage(), 400);
    }
}
