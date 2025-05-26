package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.OrderItemDTO;
import com.fooddelivery.food_delivery_app.entity.OrderItem;
import com.fooddelivery.food_delivery_app.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T15:46:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO.OrderItemDTOBuilder orderItemDTO = OrderItemDTO.builder();

        orderItemDTO.productId( orderItemProductId( orderItem ) );
        orderItemDTO.productName( orderItemProductName( orderItem ) );
        orderItemDTO.quantity( orderItem.getQuantity() );

        orderItemDTO.price( orderItem.getProduct().getPrice() );

        return orderItemDTO.build();
    }

    @Override
    public OrderItem toEntity(OrderItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.product( orderItemDTOToProduct( dto ) );
        orderItem.quantity( dto.getQuantity() );
        orderItem.price( dto.getPrice() );

        return orderItem.build();
    }

    private Long orderItemProductId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderItemProductName(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Product orderItemDTOToProduct(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( orderItemDTO.getProductId() );

        return product;
    }
}
