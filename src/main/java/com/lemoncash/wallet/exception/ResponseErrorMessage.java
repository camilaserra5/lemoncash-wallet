package com.lemoncash.wallet.exception;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrorMessage {
    private final int status;
    private final String message;
    private final List<FieldErrorMessage> fieldErrors = new ArrayList<>();

    ResponseErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String path, String message) {
        FieldErrorMessage error = new FieldErrorMessage(path, message);
        fieldErrors.add(error);
    }

    public List<FieldErrorMessage> getFieldErrors() {
        return fieldErrors;
    }

}
