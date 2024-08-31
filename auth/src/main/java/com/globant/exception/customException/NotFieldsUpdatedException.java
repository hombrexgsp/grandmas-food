package com.globant.exception.customException;

import domain.http.error.ErrorCode;

public class NotFieldsUpdatedException extends RuntimeException {
    public NotFieldsUpdatedException(String message) {
        super(message);
    }
    public ErrorCode getErrorCode() {
        return ErrorCode.VALIDATION_ERROR;
    }
}
