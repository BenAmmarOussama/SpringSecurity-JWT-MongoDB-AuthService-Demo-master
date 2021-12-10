package com.example.demojwt.jwt;

import com.example.demojwt.service.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";
    private int jwtExpirationMs = 1000 * 60 * 10;


    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getRole());
        claims.put("email", userDetails.getEmail());
        claims.put("name", userDetails.getName());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getId()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}