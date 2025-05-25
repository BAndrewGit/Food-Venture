package com.fooddelivery.food_delivery_app.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "ID-ul produsului este obligatoriu")
    private Long productId;
    private String productName;
    @NotNull(message = "Cantitatea este obligatorie")
    @Min(value = 1, message = "Cantitatea minimă este 1")
    private Integer quantity;
    private Double price;
}
