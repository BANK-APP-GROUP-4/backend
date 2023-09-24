package com.wellsfargo.bankapp.exception;

public class SavingsAccountNotFoundException extends RuntimeException{
    public SavingsAccountNotFoundException(String message) {
        super(message);
    }
}
