package com.wellsfargo.bankapp.exception;

public class BalanceInsufficientException extends RuntimeException {
	public BalanceInsufficientException(String message) {
        super(message);
    }
}
