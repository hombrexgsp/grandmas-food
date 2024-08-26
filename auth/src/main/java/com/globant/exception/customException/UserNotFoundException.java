package com.globant.exception.customException;

import com.globant.exception.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.USER_NOT_FOUND;
    }
}
