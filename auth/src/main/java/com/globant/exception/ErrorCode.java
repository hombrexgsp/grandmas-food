package com.globant.exception;

public enum ErrorCode {

    USER_NOT_FOUND("E1001"),
    DUPLICATE_USER("E1002"),
    VALIDATION_ERROR("E1003"),
    GENERAL_ERROR("E9999");

    private final String code;

    ErrorCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
