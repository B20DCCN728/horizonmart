package org.example.orderservice.controller;

import org.example.orderservice.dto.OrderCreateDto;
import org.example.orderservice.dto.OrderResponseDto;
import org.example.orderservice.model.Order;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get order by ID
    @GetMapping("/get/{id}")
    public OrderResponseDto get(@PathVariable Long id) {
        return orderService.get(id).orElse(null);
    }

    // Get all
    @GetMapping("/get-all")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    // Create order
    @PostMapping("/create")
    public Boolean create(@RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }
}
