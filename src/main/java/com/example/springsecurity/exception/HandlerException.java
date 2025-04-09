package com.example.springsecurity.exception;

import com.example.springsecurity.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerAllException(Exception e) {
        return new ResponseDTO(e.getLocalizedMessage(), 400);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerRuntimeException(RuntimeException e) {
        return new ResponseDTO(e.getMessage(), 400);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handInvalidArgument(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public ResponseEntity<ResponseDTO<String>> handingUnAuthoriseException(InternalAuthenticationServiceException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>("Unauthorized", 401));
    }

}
