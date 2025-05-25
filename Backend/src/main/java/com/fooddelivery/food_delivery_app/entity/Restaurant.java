package com.fooddelivery.food_delivery_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String description;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cuisine_type")
    private String cuisineType;

    private Boolean active = true;

    @Column(name = "delivery_fee")
    private Double deliveryFee;

    @Column(name = "delivery_range")
    private Integer deliveryRange; // în km

    @Column(name = "min_order_value")
    private Double minOrderValue;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantSchedule> schedules = new ArrayList<>();

    @Column(name = "image_data")
    private byte[] imageData;
}
