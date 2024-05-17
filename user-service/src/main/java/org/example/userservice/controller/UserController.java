package org.example.userservice.controller;

import org.example.userservice.dto.UserCreateDto;
import org.example.userservice.dto.UserResponseDto;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    // Add a new method to the UserController class that create a new User object.
    @PostMapping("/create")
    public Boolean create(@RequestBody UserCreateDto user) {
        return userService.create(user);
    }
    // Add a new method to the UserController class that returns all User objects.
    @GetMapping("/all")
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }
    // Add a new method to the UserController class that returns a User object by its ID.
    @GetMapping("/get/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.get(id).orElse(null);
    }
}
