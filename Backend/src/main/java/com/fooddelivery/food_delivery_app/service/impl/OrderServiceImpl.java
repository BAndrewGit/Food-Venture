package com.fooddelivery.food_delivery_app.service.impl;

import com.fooddelivery.food_delivery_app.dto.OrderDTO;
import com.fooddelivery.food_delivery_app.dto.OrderItemDTO;
import com.fooddelivery.food_delivery_app.entity.Order;
import com.fooddelivery.food_delivery_app.entity.OrderItem;
import com.fooddelivery.food_delivery_app.entity.Product;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.mapper.OrderMapper;
import com.fooddelivery.food_delivery_app.repository.OrderRepository;
import com.fooddelivery.food_delivery_app.repository.ProductRepository;
import com.fooddelivery.food_delivery_app.repository.UserRepository;
import com.fooddelivery.food_delivery_app.service.OrderService;
import com.fooddelivery.food_delivery_app.service.domain.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository; // <-- adaugă această linie!
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderDomainService orderDomainService;

    @Override
    public OrderDTO placeOrder(OrderDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu există"));

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("NEW");
        order.setAddress(dto.getAddress());

        // Delivery fee poate fi setat dinamic, aici e hardcodat pentru exemplu
        order.setDeliveryFee(5.0);

        double subtotal = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produsul nu există: " + itemDTO.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());
            orderItems.add(item);

            subtotal += product.getPrice() * itemDTO.getQuantity();
        }

        order.setItems(orderItems);
        order.setSubtotal(subtotal);
        order.setTotalPrice(subtotal + order.getDeliveryFee());

        orderDomainService.validateOrder(order, user);

        Order saved = orderRepository.save(order);

        // Marchează userul ca "nu mai e nou" dacă a trecut validarea
        if (Boolean.TRUE.equals(user.getIsNewUser())) {
            user.setIsNewUser(false);
            userRepository.save(user);
        }

        return orderMapper.toDTO(saved);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<OrderDTO> getUserOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu există"));
        return orderRepository.findByUser(user)
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(Long orderId, String username) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Comanda nu există"));
        if (!order.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Nu poți anula comenzi ale altor utilizatori.");
        }
        if (!order.getStatus().equals("NEW")) {
            throw new RuntimeException("Comanda nu mai poate fi anulată.");
        }
        order.setStatus("CANCELED");
        orderRepository.save(order);
    }
}
