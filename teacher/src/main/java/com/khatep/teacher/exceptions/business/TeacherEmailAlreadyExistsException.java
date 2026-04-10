package com.khatep.teacher.exceptions.business;

import com.khatep.teacher.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherEmailAlreadyExistsException extends BaseException {
    public TeacherEmailAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT, "Teacher email already exists");
    }
}
