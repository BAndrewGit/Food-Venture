package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.RestaurantDTO;
import com.fooddelivery.food_delivery_app.dto.RestaurantScheduleDTO;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.entity.RestaurantSchedule;
import java.util.ArrayList;
import java.util.Arrays;
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
public class RestaurantMapperImpl implements RestaurantMapper {

    @Autowired
    private RestaurantScheduleMapper restaurantScheduleMapper;

    @Override
    public RestaurantDTO toDTO(Restaurant entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId( entity.getId() );
        restaurantDTO.setName( entity.getName() );
        restaurantDTO.setAddress( entity.getAddress() );
        restaurantDTO.setDescription( entity.getDescription() );
        restaurantDTO.setPhoneNumber( entity.getPhoneNumber() );
        restaurantDTO.setCuisineType( entity.getCuisineType() );
        restaurantDTO.setActive( entity.getActive() );
        restaurantDTO.setDeliveryFee( entity.getDeliveryFee() );
        restaurantDTO.setDeliveryRange( entity.getDeliveryRange() );
        restaurantDTO.setMinOrderValue( entity.getMinOrderValue() );
        restaurantDTO.setSchedules( restaurantScheduleListToRestaurantScheduleDTOList( entity.getSchedules() ) );
        byte[] imageData = entity.getImageData();
        if ( imageData != null ) {
            restaurantDTO.setImageData( Arrays.copyOf( imageData, imageData.length ) );
        }

        return restaurantDTO;
    }

    @Override
    public Restaurant toEntity(RestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setId( dto.getId() );
        restaurant.setName( dto.getName() );
        restaurant.setAddress( dto.getAddress() );
        restaurant.setDescription( dto.getDescription() );
        restaurant.setPhoneNumber( dto.getPhoneNumber() );
        restaurant.setCuisineType( dto.getCuisineType() );
        restaurant.setActive( dto.getActive() );
        restaurant.setDeliveryFee( dto.getDeliveryFee() );
        restaurant.setDeliveryRange( dto.getDeliveryRange() );
        restaurant.setMinOrderValue( dto.getMinOrderValue() );
        restaurant.setSchedules( restaurantScheduleDTOListToRestaurantScheduleList( dto.getSchedules() ) );
        byte[] imageData = dto.getImageData();
        if ( imageData != null ) {
            restaurant.setImageData( Arrays.copyOf( imageData, imageData.length ) );
        }

        return restaurant;
    }

    protected List<RestaurantScheduleDTO> restaurantScheduleListToRestaurantScheduleDTOList(List<RestaurantSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<RestaurantScheduleDTO> list1 = new ArrayList<RestaurantScheduleDTO>( list.size() );
        for ( RestaurantSchedule restaurantSchedule : list ) {
            list1.add( restaurantScheduleMapper.toDTO( restaurantSchedule ) );
        }

        return list1;
    }

    protected List<RestaurantSchedule> restaurantScheduleDTOListToRestaurantScheduleList(List<RestaurantScheduleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<RestaurantSchedule> list1 = new ArrayList<RestaurantSchedule>( list.size() );
        for ( RestaurantScheduleDTO restaurantScheduleDTO : list ) {
            list1.add( restaurantScheduleMapper.toEntity( restaurantScheduleDTO ) );
        }

        return list1;
    }
}
