package com.example.core10validexception.controller;

import com.example.core10validexception.dto.ValidationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class ValidationController {

    @PostMapping("/validation")
    public String checkValidation(@Valid @RequestBody ValidationRequest dto) {
        return dto.toString();
    }
}
