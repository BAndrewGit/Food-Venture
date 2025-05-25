package com.fooddelivery.food_delivery_app.service.domain;

import com.fooddelivery.food_delivery_app.entity.Order;
import com.fooddelivery.food_delivery_app.entity.User;
import org.springframework.stereotype.Service;

@Service
public class OrderDomainService {

    public void validateOrder(Order order, User user) {
        if (user.getIsNewUser() != null && user.getIsNewUser() && order.getTotalPrice() < 20.0) {
            throw new IllegalArgumentException("Comenzile utilizatorilor noi trebuie să fie de cel puțin 20 RON.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Comanda trebuie să conțină cel puțin un produs.");
        }

        if (order.getTotalPrice() == null || order.getTotalPrice() <= 0) {
            throw new IllegalArgumentException("Totalul comenzii trebuie să fie valid.");
        }
    }
}
