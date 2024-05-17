package org.example.orderservice.controller;

import org.example.orderservice.dto.OrderResponseDto;
import org.example.orderservice.model.Order;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get order by ID
    @GetMapping("/get/{id}")
    public Order get(@PathVariable Long id) {
        return orderService.get(id).orElse(null);
    }

    // Get all
    @GetMapping("/get-all")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }
}
