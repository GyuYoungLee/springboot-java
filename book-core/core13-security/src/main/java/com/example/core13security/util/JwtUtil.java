package com.example.core13security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static Key createKey(String jwtSecret) {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 인증 토큰 발급
    public static String createToken(String username, String jwtSecret, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(createKey(jwtSecret), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 유효성 체크
    public static boolean isExpired(String token, String jwtSecret) {
        return Jwts.parserBuilder().setSigningKey(createKey(jwtSecret)).build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());
    }

    // 토큰에서 username 추출
    public static String getUsername(String token, String jwtSecret) {
        return Jwts.parserBuilder().setSigningKey(createKey(jwtSecret)).build()
                .parseClaimsJws(token)
                .getBody()
                .get("username", String.class);
    }
}
