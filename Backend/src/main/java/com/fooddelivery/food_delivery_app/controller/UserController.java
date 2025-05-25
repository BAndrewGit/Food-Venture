package com.fooddelivery.food_delivery_app.controller;

import com.fooddelivery.food_delivery_app.dto.UserDTO;
import com.fooddelivery.food_delivery_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getCurrentUser(userDetails.getUsername()));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                              @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDetails.getUsername(), userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
