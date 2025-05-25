package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.UserDTO;

public interface UserService {
    UserDTO getCurrentUser(String username);
    UserDTO updateUser(String username, UserDTO updatedData);
    void deleteUser(String username);
}
