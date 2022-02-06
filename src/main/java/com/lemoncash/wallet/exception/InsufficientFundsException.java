package com.lemoncash.wallet.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }

}
