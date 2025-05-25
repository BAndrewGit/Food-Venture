package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
