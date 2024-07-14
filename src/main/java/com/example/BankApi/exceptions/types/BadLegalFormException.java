package com.example.BankApi.exceptions.types;

public class BadLegalFormException extends RuntimeException {
    public BadLegalFormException(String message)
    {
        super(message);
    }

    public BadLegalFormException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
