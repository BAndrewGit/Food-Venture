package com.fooddelivery.food_delivery_app.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    @Size(max = 100, message = "Numele trebuie să fie sub 100 caractere")
    private String name;

    @NotNull(message = "Prețul este obligatoriu")
    @Positive(message = "Prețul trebuie să fie pozitiv")
    private Double price;

    @Size(max = 500, message = "Descrierea trebuie să fie sub 500 caractere")
    private String description;

    @NotNull(message = "Disponibilitatea este obligatorie")
    private Boolean available;

    // Opțional pentru imagine Base64
    private String imageBase64;

    // Add this field to store the restaurant ID
    private Long restaurantId;
}
