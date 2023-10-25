package com.example.core10validexception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final String type;
    private final HttpStatus httpStatus;

    public CustomException(String type, HttpStatus httpStatus, String message) {
        super(message);
        this.type = type;
        this.httpStatus = httpStatus;
    }
}
