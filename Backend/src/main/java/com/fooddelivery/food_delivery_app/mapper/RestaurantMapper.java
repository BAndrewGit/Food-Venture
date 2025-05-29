package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.RestaurantDTO;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RestaurantScheduleMapper.class})
public interface RestaurantMapper {
    RestaurantDTO toDTO(Restaurant entity);
    Restaurant toEntity(RestaurantDTO dto);
}