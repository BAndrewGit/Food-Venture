package com.fooddelivery.food_delivery_app.dto.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username-ul este obligatoriu")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "Parola este obligatorie")
    @Size(min = 6, message = "Parola trebuie să aibă cel puțin 6 caractere")
    private String password;
}
