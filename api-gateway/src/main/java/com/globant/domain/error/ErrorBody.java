package com.globant.domain.error;

import java.time.LocalDateTime;

public record ErrorBody(
        String code,
        LocalDateTime timestamp,
        String description,
        String exception
) {}
