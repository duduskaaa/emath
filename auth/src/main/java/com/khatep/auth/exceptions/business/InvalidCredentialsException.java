package com.khatep.auth.exceptions.business;

import com.khatep.auth.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
}
