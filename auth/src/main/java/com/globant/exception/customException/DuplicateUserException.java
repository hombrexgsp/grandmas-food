package com.globant.exception.customException;

import com.globant.exception.ErrorCode;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.DUPLICATE_USER;
    }

}
