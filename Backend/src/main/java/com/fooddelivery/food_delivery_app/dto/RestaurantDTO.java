package com.fooddelivery.food_delivery_app.dto;

import lombok.Data;
import java.util.List;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String phoneNumber;
    private String cuisineType;
    private Boolean active;
    private Double deliveryFee;
    private Integer deliveryRange;
    private Double minOrderValue;
    private List<RestaurantScheduleDTO> schedules;
    private String imageBase64;

}
