package org.example.orderservice.service;

import org.example.orderservice.dto.OrderCreateDto;
import org.example.orderservice.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    Optional<OrderResponseDto> get(Long id);
    List<OrderResponseDto> getAll();
    Boolean create(OrderCreateDto orderCreateDto);
}
