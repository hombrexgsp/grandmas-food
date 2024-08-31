package com.globant.exception.customException;

import domain.http.error.ErrorCode;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<String> validationErrors;

    public ValidationException(List<String> validationErrors){
        super("Validation failed");
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors(){
        return validationErrors;
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.VALIDATION_ERROR;
    }

}
