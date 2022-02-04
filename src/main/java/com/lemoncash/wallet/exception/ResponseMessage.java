package com.lemoncash.wallet.exception;

import lombok.Getter;

@Getter
public class ResponseMessage {

    private final int status;
    private final String message;

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
