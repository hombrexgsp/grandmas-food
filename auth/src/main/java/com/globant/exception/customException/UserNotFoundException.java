package com.globant.exception.customException;


import domain.http.error.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.USER_NOT_FOUND;
    }
}
