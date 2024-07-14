package com.example.BankApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.BankApi.exceptions.types.BadRequestException;
import com.example.BankApi.exceptions.types.EntityNotFoundException;

@ControllerAdvice
public class BankApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException)
    {
        BankApiException exception = new BankApiException(
            entityNotFoundException.getMessage(), 
            entityNotFoundException.getCause(), 
            HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
    
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException)
    {
        BankApiException exception = new BankApiException(
            badRequestException.getMessage(), 
            badRequestException.getCause(), 
            HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
}