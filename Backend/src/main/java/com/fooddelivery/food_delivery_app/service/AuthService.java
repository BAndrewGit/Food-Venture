package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationRequest;
import com.fooddelivery.food_delivery_app.dto.auth.AuthenticationResponse;
import com.fooddelivery.food_delivery_app.dto.auth.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
