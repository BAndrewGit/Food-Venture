package com.fooddelivery.food_delivery_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    private String status;

    @Column(name = "delivery_fee", nullable = false)
    private Double deliveryFee;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
