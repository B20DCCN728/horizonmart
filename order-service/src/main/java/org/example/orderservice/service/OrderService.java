package org.example.orderservice.service;

import org.example.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {
    Optional<Order> get(Long id);
}
