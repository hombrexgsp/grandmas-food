package com.globant.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String code;
    private final LocalDateTime timestamp;
    private final String description;
    private final String exception;

    public ErrorResponse(ErrorCode errorCode, String description, String exception) {
        this.code = errorCode.getCode();
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getException() {
        return exception;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
