package com.example.core13security.controller;

import com.example.core13security.dto.JoinRequest;
import com.example.core13security.dto.LoginRequest;
import com.example.core13security.entity.User;
import com.example.core13security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest dto) {
        String token = userService.login(dto.getUsername(), dto.getPassword());
        return "token is " + token;
    }

    @PostMapping("/auth/join")
    public String join(@RequestBody JoinRequest dto) {
        User joined = userService.join(dto.getUsername(), dto.getPassword());
        return joined.getUsername() + " joined";
    }

    @GetMapping("/users/whoami")
    public String getUserInfo(Authentication authentication) {
        return "You're " + authentication.getName();
    }
}
