package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.RestaurantSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantScheduleRepository extends JpaRepository<RestaurantSchedule, Long> {
}