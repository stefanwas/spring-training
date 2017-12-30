package com.stefan.training.spring.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String cretePasswordHash(String password) {
        String hash = passwordEncoder.encode(password);
        return hash;
    }
}
