package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
