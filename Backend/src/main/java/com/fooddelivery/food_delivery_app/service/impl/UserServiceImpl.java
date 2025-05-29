package com.fooddelivery.food_delivery_app.service.impl;

import com.fooddelivery.food_delivery_app.dto.UserDTO;
import com.fooddelivery.food_delivery_app.dto.UserUpdateDTO;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.mapper.UserMapper;
import com.fooddelivery.food_delivery_app.repository.UserRepository;
import com.fooddelivery.food_delivery_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(String username, UserUpdateDTO updatedData) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));

        user.setEmail(updatedData.getEmail());
        user.setUsername(updatedData.getUsername());

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));
        userRepository.delete(user);
    }
}
