package com.bookingsystem.concurrentbooking.controller;

import com.bookingsystem.concurrentbooking.dto.LoginRequest;
import com.bookingsystem.concurrentbooking.dto.SignUpRequest;
import com.bookingsystem.concurrentbooking.security.TokenBlackListService;
import com.bookingsystem.concurrentbooking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenBlackListService tokenBlackListService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.register(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public  ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        tokenBlackListService.blackListToken(token);
        return ResponseEntity.ok("logout successful");
    }
}
