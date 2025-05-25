package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.Order;
import com.fooddelivery.food_delivery_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);
}
