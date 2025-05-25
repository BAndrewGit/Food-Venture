package com.fooddelivery.food_delivery_app.service.impl;

import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationRequest;
import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationResponse;
import com.fooddelivery.food_delivery_app.dto.auth.RegisterRequest;
import com.fooddelivery.food_delivery_app.entity.Role;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.repository.UserRepository;
import com.fooddelivery.food_delivery_app.security.JwtTokenUtil;
import com.fooddelivery.food_delivery_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email-ul este deja folosit.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .isNewUser(true)
                .build();

        userRepository.save(user);

        String token = jwtTokenUtil.generateToken(user);
        String refresh = jwtTokenUtil.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refresh)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilizator inexistent"));

        String token = jwtTokenUtil.generateToken(user);
        String refresh = jwtTokenUtil.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refresh)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
