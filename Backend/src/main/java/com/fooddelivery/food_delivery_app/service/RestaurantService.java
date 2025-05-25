package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.RestaurantDTO;
import java.util.List;

public interface RestaurantService {
    RestaurantDTO create(RestaurantDTO dto);
    List<RestaurantDTO> getAll();
    RestaurantDTO getById(Long id);
    void delete(Long id);
    RestaurantDTO update(Long id, RestaurantDTO dto);
}
