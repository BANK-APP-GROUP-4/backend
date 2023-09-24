package com.wellsfargo.bankapp.exception;

public class FDAccountNotFoundException extends RuntimeException{
    public FDAccountNotFoundException(String message) {
        super(message);
    }
}

