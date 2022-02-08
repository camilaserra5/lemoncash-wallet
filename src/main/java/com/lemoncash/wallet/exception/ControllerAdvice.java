package com.lemoncash.wallet.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseMessage handleMissingParams(MissingServletRequestParameterException ex) {
        return new ResponseMessage(BAD_REQUEST.value(), String.format("'%s' parameter is missing", ex.getParameterName()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseMessage handleConstraintValidation(ConstraintViolationException ex) {
        return new ResponseMessage(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseMessage handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseMessage(BAD_REQUEST.value(), String.format("'%s' parameter should be of type %s", ex.getName(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseErrorMessage handleNotValidRequest(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseMessage handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseMessage handleInsufficientFunds(InsufficientFundsException ex) {
        return new ResponseMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    private ResponseErrorMessage processFieldErrors(List<FieldError> fieldErrors) {
        ResponseErrorMessage error = new ResponseErrorMessage(BAD_REQUEST.value(), "validation error");
        for (FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseMessage handleSQLConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        return new ResponseMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }


}
