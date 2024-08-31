package com.globant.exception.customException;

import domain.http.error.ErrorCode;

public class InvalidOrIncompleteUserException extends RuntimeException {
    public InvalidOrIncompleteUserException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
      return ErrorCode.VALIDATION_ERROR;
    }
}
