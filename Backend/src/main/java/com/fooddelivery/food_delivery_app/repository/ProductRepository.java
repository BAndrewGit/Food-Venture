package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByRestaurantId(Long restaurantId);
    List<Product> findByAvailableTrue();
}
