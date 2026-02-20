package com.khatep.teacher.exceptions.handler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorTitle;

    protected BaseException(String message, HttpStatus httpStatus, String errorTitle) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorTitle = errorTitle;
    }
}