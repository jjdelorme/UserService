/*
 * Copyright © 2025 ACME Corp.
 *
 * This work is protected by copyright law and international treaties.
 * Unauthorized reproduction or distribution of this work, or any portion
 * thereof, may result in severe civil and criminal penalties, and will be
 * prosecuted to the maximum extent possible under the law.
 *
 * ACME Corp. Confidential. All Rights Reserved.
 *
 * This software contains the proprietary and confidential information of
 * ACME Corp., and may not be copied, reproduced, or distributed in any
 * form without the express written permission of ACME Corp.
 */

package com.acme.userservice.controller;

import com.acme.userservice.dto.AddressDTO;
import com.acme.userservice.dto.UserDataDTO;
import com.acme.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Base path for user-related requests
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDataDTO>> getAllUsers() {
        List<UserDataDTO> users = userService.getAllUsers(); // Assuming this method exists in UserService
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDataDTO> getUserById(@PathVariable Long userId) {
        UserDataDTO user = userService.getUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDataDTO userDataDTO) {
        Long userId = userService.createUser(userDataDTO);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDataDTO userDataDTO) {
        userDataDTO.setUserId(userId); // Ensure the ID in the path matches the DTO
        userService.updateUser(userDataDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<Void> updateUserAddress(@PathVariable Long userId, @RequestBody AddressDTO addressDTO) {
        UserDataDTO user = userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Ensure the DTO contains a valid addressId, or provide logic to generate/assign one
        if (addressDTO.getAddressId() == null) {
          // Generate a new address ID or provide a default value
          // Example (you would likely want to manage this more robustly)
          addressDTO.setAddressId(1L);
        }
        user.setAddress(addressDTO);
        userService.updateUser(user); // Assuming updateUser can handle partial updates
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
