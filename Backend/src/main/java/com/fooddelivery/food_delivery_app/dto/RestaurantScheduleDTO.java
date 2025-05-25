package com.fooddelivery.food_delivery_app.dto;

import lombok.Data;

@Data
public class RestaurantScheduleDTO {
    private Long id;
    private String dayOfWeek;
    private String openingTime;
    private String closingTime;
    private Boolean closed;
}
