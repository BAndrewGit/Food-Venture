package com.fooddelivery.food_delivery_app.service.impl;

import com.fooddelivery.food_delivery_app.dto.RestaurantDTO;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.mapper.RestaurantMapper;
import com.fooddelivery.food_delivery_app.repository.RestaurantRepository;
import com.fooddelivery.food_delivery_app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public RestaurantDTO create(RestaurantDTO dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        if (restaurant.getSchedules() != null) {
            restaurant.getSchedules().forEach(s -> s.setRestaurant(restaurant));
        }
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    @Override
    public List<RestaurantDTO> getAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurantMapper.toDTO(restaurant);
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantDTO update(Long id, RestaurantDTO dto) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Restaurant updated = restaurantMapper.toEntity(dto);
        updated.setId(existing.getId());
        if (updated.getSchedules() != null) {
            updated.getSchedules().forEach(s -> s.setRestaurant(updated));
        }
        return restaurantMapper.toDTO(restaurantRepository.save(updated));
    }
}
