package com.khatep.teacher.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        org.springframework.http.HttpStatus status,
        String error,
        String message
) {}
