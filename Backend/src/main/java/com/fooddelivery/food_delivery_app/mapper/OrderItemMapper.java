package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.OrderItemDTO;
import com.fooddelivery.food_delivery_app.entity.OrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    // Entity -> DTO
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(expression = "java(orderItem.getProduct().getPrice())", target = "price")
    OrderItemDTO toDTO(OrderItem orderItem);

    // DTO -> Entity
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);
}
