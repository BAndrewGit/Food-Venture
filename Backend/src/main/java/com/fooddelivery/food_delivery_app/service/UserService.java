package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.UserDTO;
import com.fooddelivery.food_delivery_app.dto.UserUpdateDTO;

public interface UserService {
    UserDTO getCurrentUser(String username);
    UserDTO updateUser(String username, UserUpdateDTO updatedData);
    void deleteUser(String username);
}
