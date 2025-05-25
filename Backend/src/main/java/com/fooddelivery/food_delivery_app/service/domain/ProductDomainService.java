package com.fooddelivery.food_delivery_app.service.domain;

import com.fooddelivery.food_delivery_app.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {

    public void validateProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Prețul produsului trebuie să fie pozitiv.");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Numele produsului nu poate fi gol.");
        }

        if (!product.isAvailable()) {
            product.setAvailable(true);
        }
    }
}