package com.khatep.teacher.exceptions.business;

import com.khatep.teacher.exceptions.handler.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherNotFoundByEmailException extends BaseException {
    public TeacherNotFoundByEmailException(String message) {
        super(message, HttpStatus.NOT_FOUND, "Teacher not found");
    }
}
