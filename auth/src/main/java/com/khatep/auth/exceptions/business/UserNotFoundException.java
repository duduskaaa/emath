package com.khatep.auth.exceptions.business;

import com.khatep.auth.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "Teacher not found");
    }
}
