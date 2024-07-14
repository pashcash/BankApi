package com.example.BankApi.exceptions;

import org.springframework.http.HttpStatus;

public class BankApiException {
    private String message;
    private Throwable cause;
    private HttpStatus httpStatus;
    
    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BankApiException(String message, Throwable cause, HttpStatus httpStatus) {
        this.message = message;
        this.cause = cause;
        this.httpStatus = httpStatus;
    }
}
