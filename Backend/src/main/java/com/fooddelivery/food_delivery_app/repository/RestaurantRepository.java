package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}