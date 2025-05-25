package com.fooddelivery.food_delivery_app.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Double totalPrice;
    private Double deliveryFee;
    private Double subtotal;
    private String status;
    private Long userId;
    private String address;
    @NotEmpty(message = "Comanda trebuie să conțină cel puțin un produs")
    private List<OrderItemDTO> items;
}
