package org.example.orderservice.controller;

import jakarta.ws.rs.Path;
import org.example.orderservice.dto.OrderCreateDto;
import org.example.orderservice.dto.OrderResponseDto;
import org.example.orderservice.dto.ProductStatDto;
import org.example.orderservice.model.Order;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Get order by ID
    @GetMapping("/get/{id}")
    public OrderResponseDto get(@PathVariable Long id) {
        return orderService.get(id).orElse(null);
    }

    // Get all orders
    @GetMapping("/get-all")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    // Create order
    @PostMapping("/create")
    public Boolean create(@RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }

    // Get total revenue
    @GetMapping("/get-total-revenue/{f}/{t}")
    public ProductStatDto getTotalRevenue(@PathVariable String f, @PathVariable String t) {
        LocalDateTime from = LocalDateTime.parse(f, dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse(t, dateTimeFormatter);
        return orderService.getProductStat(from, to);
    }
}
