package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.RestaurantScheduleDTO;
import com.fooddelivery.food_delivery_app.entity.RestaurantSchedule;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T15:46:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class RestaurantScheduleMapperImpl implements RestaurantScheduleMapper {

    @Override
    public RestaurantScheduleDTO toDTO(RestaurantSchedule entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantScheduleDTO restaurantScheduleDTO = new RestaurantScheduleDTO();

        restaurantScheduleDTO.setId( entity.getId() );
        if ( entity.getDayOfWeek() != null ) {
            restaurantScheduleDTO.setDayOfWeek( entity.getDayOfWeek().name() );
        }
        if ( entity.getOpeningTime() != null ) {
            restaurantScheduleDTO.setOpeningTime( DateTimeFormatter.ISO_LOCAL_TIME.format( entity.getOpeningTime() ) );
        }
        if ( entity.getClosingTime() != null ) {
            restaurantScheduleDTO.setClosingTime( DateTimeFormatter.ISO_LOCAL_TIME.format( entity.getClosingTime() ) );
        }
        restaurantScheduleDTO.setClosed( entity.getClosed() );

        return restaurantScheduleDTO;
    }

    @Override
    public RestaurantSchedule toEntity(RestaurantScheduleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RestaurantSchedule restaurantSchedule = new RestaurantSchedule();

        restaurantSchedule.setId( dto.getId() );
        if ( dto.getDayOfWeek() != null ) {
            restaurantSchedule.setDayOfWeek( Enum.valueOf( DayOfWeek.class, dto.getDayOfWeek() ) );
        }
        if ( dto.getOpeningTime() != null ) {
            restaurantSchedule.setOpeningTime( LocalTime.parse( dto.getOpeningTime() ) );
        }
        if ( dto.getClosingTime() != null ) {
            restaurantSchedule.setClosingTime( LocalTime.parse( dto.getClosingTime() ) );
        }
        restaurantSchedule.setClosed( dto.getClosed() );

        return restaurantSchedule;
    }
}
