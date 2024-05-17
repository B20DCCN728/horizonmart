package org.example.userservice.service;

import org.example.userservice.dto.UserCreateDto;
import org.example.userservice.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Boolean create(UserCreateDto user);
    Optional<UserResponseDto> get(Long id);
    List<UserResponseDto> getAll();
}
