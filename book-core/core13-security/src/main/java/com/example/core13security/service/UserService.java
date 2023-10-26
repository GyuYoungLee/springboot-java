package com.example.core13security.service;

import com.example.core13security.entity.User;
import com.example.core13security.repository.UserRepository;
import com.example.core13security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private final long expiredMs = 1000L * 60 * 60 * 24;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원 아님"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException("비밀번호 틀림");

        return JwtUtil.createToken(username, jwtSecret, expiredMs);
    }

    public User join(String username, String password) {
        return userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build());
    }
}
