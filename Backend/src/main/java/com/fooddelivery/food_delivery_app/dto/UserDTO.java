package com.fooddelivery.food_delivery_app.dto;

import com.fooddelivery.food_delivery_app.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username-ul este obligatoriu")
    @Size(min = 3, max = 50, message = "Username-ul trebuie să fie între 3 și 50 caractere")
    private String username;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email invalid")
    private String email;

    @NotNull(message = "Rolul este obligatoriu")
    private Role role;

    private Boolean isNewUser;
}
