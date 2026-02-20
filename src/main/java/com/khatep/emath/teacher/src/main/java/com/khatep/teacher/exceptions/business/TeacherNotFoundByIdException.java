package com.khatep.teacher.exceptions.business;

import com.khatep.teacher.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherNotFoundByIdException extends BaseException {
    public TeacherNotFoundByIdException(String message) {
        super(message, HttpStatus.NOT_FOUND, "Teacher not found");
    }
}
