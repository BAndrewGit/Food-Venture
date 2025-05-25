package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO dto, String username);
    List<OrderDTO> getUserOrders(String username);
    void cancelOrder(Long orderId, String username);
}
