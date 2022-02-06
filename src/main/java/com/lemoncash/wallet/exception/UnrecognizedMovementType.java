package com.lemoncash.wallet.exception;

public class UnrecognizedMovementType extends RuntimeException {

    public UnrecognizedMovementType(String errorMessage) {
        super(errorMessage);
    }

}
