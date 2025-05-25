package com.fooddelivery.food_delivery_app.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Username-ul este obligatoriu")
    private String username;

    @NotBlank(message = "Parola este obligatorie")
    private String password;
}
