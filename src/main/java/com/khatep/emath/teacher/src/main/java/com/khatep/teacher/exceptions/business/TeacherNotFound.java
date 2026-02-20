package com.khatep.teacher.exceptions.business;

import com.khatep.teacher.exceptions.handler.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherNotFound extends BaseException {
    public TeacherNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND, "Teacher not found");
    }
}
