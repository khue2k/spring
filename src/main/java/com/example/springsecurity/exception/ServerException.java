package com.example.springsecurity.exception;

public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}
