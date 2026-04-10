package com.khatep.auth.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        org.springframework.http.HttpStatus status,
        String error,
        String message
) {}
