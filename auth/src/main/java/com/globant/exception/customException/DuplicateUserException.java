package com.globant.exception.customException;


import domain.http.error.ErrorCode;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.DUPLICATE_USER;
    }

}
