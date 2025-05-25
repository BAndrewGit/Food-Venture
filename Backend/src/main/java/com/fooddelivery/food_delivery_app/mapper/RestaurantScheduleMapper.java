package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.RestaurantScheduleDTO;
import com.fooddelivery.food_delivery_app.entity.RestaurantSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantScheduleMapper {
    RestaurantScheduleDTO toDTO(RestaurantSchedule entity);
    RestaurantSchedule toEntity(RestaurantScheduleDTO dto);
}
