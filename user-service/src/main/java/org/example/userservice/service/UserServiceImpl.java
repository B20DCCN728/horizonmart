package org.example.userservice.service;

import org.example.userservice.dto.UserCreateDto;
import org.example.userservice.dto.UserResponseDto;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean create(UserCreateDto user) {
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            return false;
        return getaBoolean(user);
    }

    private Boolean getaBoolean(UserCreateDto user) {
        User upUser = new User();
        upUser.setFullName(user.getFullName());
        upUser.setPhoneNumber(user.getPhoneNumber());
        upUser.setNote(user.getNote());
        userRepository.save(upUser);
        return true;
    }

    @Override
    public Optional<UserResponseDto> get(Long id) {
        return userRepository.findById(id)
                .map(
                        user -> new UserResponseDto(
                                user.getID(),
                                user.getFullName(),
                                user.getPhoneNumber(),
                                user.getNote()
                        )
                );
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(
                        user -> new UserResponseDto(
                                user.getID(),
                                user.getFullName(),
                                user.getPhoneNumber(),
                                user.getNote()
                        )
                ).toList();
    }

    @Override
    public List<UserResponseDto> search(String phoneNumber) {
        return userRepository.findAllByPhoneNumberContaining(phoneNumber).stream()
                .map(
                        user -> new UserResponseDto(
                                user.getID(),
                                user.getFullName(),
                                user.getPhoneNumber(),
                                user.getNote()
                        )
                ).toList();
    }
}
