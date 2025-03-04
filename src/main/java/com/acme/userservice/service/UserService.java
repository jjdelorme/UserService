package com.acme.userservice.service;

import com.acme.userservice.annotation.AuditLog;
import com.acme.userservice.dto.AddressDTO;
import com.acme.userservice.dto.DivisionDTO;
import com.acme.userservice.dto.UserDataDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Added @Service annotation to make it a Spring-managed bean
public class UserService {

    @AuditLog(description = "Updates user information")
    public void updateUser(UserDataDTO userDataDTO) {
        // Logic to update user in the database
        System.out.println("Updating user: " + userDataDTO.getUserId());
    }

    @AuditLog(description = "Creates a new user")
    public Long createUser(UserDataDTO userDataDTO) {
        // Logic to create user in the database
        System.out.println("Creating user: " + userDataDTO.getFirstName());
        return 123L; // Return a sample user ID
    }

    public UserDataDTO getUser(Long userId) {
        // Logic to retrieve user from the database
        System.out.println("Getting user: " + userId);
        return new UserDataDTO()
                .setUserId(userId)
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com");
    }

    @AuditLog(description = "Deletes a user")
    public void deleteUser(Long userId) {
        // Logic to delete user from the database
        System.out.println("Deleting user: " + userId);
    }

    public void someOtherMethod() {
        // This method does not modify data, so it doesn't have @AuditLog
        System.out.println("Doing something else...");
    }

    // New method to get all users
    public List<UserDataDTO> getAllUsers() {
        // Logic to retrieve all users from the database
        System.out.println("Getting all users");
        List<UserDataDTO> users = new ArrayList<>();

        AddressDTO address1 = new AddressDTO()
                .setAddressId(101L)
                .setStreet("123 Main St")
                .setCity("Anytown")
                .setState("CA")
                .setZipCode("12345");
        DivisionDTO division1 = new DivisionDTO()
                .setDivisionId(1001L)
                .setDivisionName("Sales")
                .setDescription("Sales Division");

        AddressDTO address2 = new AddressDTO()
                .setAddressId(102L).setStreet("456 Oak Ave")
                .setCity("Springfield").setState("IL")
                .setZipCode("67890");
        DivisionDTO division2 = new DivisionDTO()
                .setDivisionId(1002L)
                .setDivisionName("Marketing")
                .setDescription("Marketing Division");
        // Add some sample users for demonstration
        users.add(new UserDataDTO()
                .setUserId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com").setAddress(address1).setDivision(division1));
        users.add(new UserDataDTO()
                .setUserId(2L)
                .setFirstName("Jane")
                .setLastName("Smith")
                .setEmail("jane.smith@example.com").setAddress(address2).setDivision(division2));

        return users;
    }

    @AuditLog(description = "Updates division information")
    public void updateDivision(DivisionDTO divisionDTO) {
        // Logic to update division in the database
        System.out.println("Updating division: " + divisionDTO.getDivisionId());
        // In a real scenario, you would interact with a database here
    }
}
