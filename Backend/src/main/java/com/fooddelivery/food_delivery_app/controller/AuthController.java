package com.fooddelivery.food_delivery_app.controller;

import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationRequest;
import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationResponse;
import com.fooddelivery.food_delivery_app.dto.auth.RegisterRequest;
import com.fooddelivery.food_delivery_app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
