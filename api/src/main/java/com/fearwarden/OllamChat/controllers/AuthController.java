package com.fearwarden.OllamChat.controllers;

import com.fearwarden.OllamChat.dto.request.LoginDto;
import com.fearwarden.OllamChat.dto.request.RegisterDto;
import com.fearwarden.OllamChat.dto.response.UserDto;
import com.fearwarden.OllamChat.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated RegisterDto body) {
        authService.register(body);
        return ResponseEntity.ok("User successfully registered.");
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Validated LoginDto body, HttpServletRequest request) {
        UserDto user = authService.login(body.getEmail(), body.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return ResponseEntity.ok(user);
    }
}
