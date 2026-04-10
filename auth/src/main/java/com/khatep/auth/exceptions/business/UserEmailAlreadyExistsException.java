package com.khatep.auth.exceptions.business;

import com.khatep.auth.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class UserEmailAlreadyExistsException extends BaseException {
    public UserEmailAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT, "Teacher email already exists");
    }
}
