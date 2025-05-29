package com.fooddelivery.food_delivery_app.controller;

import com.fooddelivery.food_delivery_app.dto.OrderDTO;
import com.fooddelivery.food_delivery_app.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@Valid @RequestBody OrderDTO dto, Principal principal) {
        String username = principal.getName();
        OrderDTO created = orderService.placeOrder(dto, username);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(orderService.getUserOrders(userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        orderService.cancelOrder(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
