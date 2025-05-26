package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.OrderDTO;
import com.fooddelivery.food_delivery_app.dto.OrderItemDTO;
import com.fooddelivery.food_delivery_app.entity.Order;
import com.fooddelivery.food_delivery_app.entity.OrderItem;
import com.fooddelivery.food_delivery_app.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T15:46:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.userId( orderUserId( order ) );
        orderDTO.id( order.getId() );
        orderDTO.createdAt( order.getCreatedAt() );
        orderDTO.totalPrice( order.getTotalPrice() );
        orderDTO.deliveryFee( order.getDeliveryFee() );
        orderDTO.subtotal( order.getSubtotal() );
        orderDTO.status( order.getStatus() );
        orderDTO.address( order.getAddress() );
        orderDTO.items( orderItemListToOrderItemDTOList( order.getItems() ) );

        return orderDTO.build();
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<OrderItemDTO> orderItemListToOrderItemDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toDTO( orderItem ) );
        }

        return list1;
    }
}
