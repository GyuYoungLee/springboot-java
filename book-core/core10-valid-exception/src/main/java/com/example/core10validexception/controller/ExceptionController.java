package com.example.core10validexception.controller;

import com.example.core10validexception.common.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class ExceptionController {

    @GetMapping("/exception")
    public void throwException() {
        throw new RuntimeException("RuntimeException happened !!!");
    }

    @GetMapping("/custom-exception")
    public void throwCustomException() {
        throw new CustomException("PRODUCT", HttpStatus.BAD_REQUEST, "CustomException happened !!!");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleException(CustomException e) {

        Map<String, String> map = new HashMap<>();
        map.put("type", e.getType());
        map.put("code", e.getHttpStatus().getReasonPhrase());
        map.put("message", e.getMessage());

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(map);
    }
}
