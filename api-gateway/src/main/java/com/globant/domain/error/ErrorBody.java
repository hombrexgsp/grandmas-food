package com.globant.domain.error;

import domain.http.error.ErrorCode;

import java.time.LocalDateTime;

public record ErrorBody(
        String code,
        LocalDateTime timestamp,
        String description,
        String exception
) {}
