package org.example.orderservice.service;

import org.example.orderservice.dto.OrderProductDto;
import org.example.orderservice.dto.OrderResponseDto;
import org.example.orderservice.dto.UserResponseDto;
import org.example.orderservice.model.Order;
import org.example.orderservice.model.OrderProduct;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }
    @Override
    public List<OrderResponseDto> getAll() {
        // Get all orders from the database
        List<Order> orders = orderRepository.findAll();
        // Create a list of OrderResponseDto objects
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        // Loop through each order
        for(Order order : orders) {
            // Create an OrderResponseDto object
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            // Set the ID of the order
            orderResponseDto.setId(order.getId());
            // Create a UserResponseDto object
            UserResponseDto userResponseDto = new UserResponseDto();
            // Set the ID of the order
            userResponseDto.setID(order.getUserId());
            // Set the user of the order
            orderResponseDto.setUser(userResponseDto);
            List<OrderProductDto> orderProductDtos = new ArrayList<>();
            for(OrderProduct orderProduct : order.getOrderProducts()) {
                OrderProductDto orderProductDto = new OrderProductDto();
                orderProductDto.setId(orderProduct.getId());
                orderProductDtos.add(orderProductDto);
            }
            orderResponseDto.setProducts(orderProductDtos);
            orderResponseDto.setTotal(order.getTotal());
            orderResponseDto.setOrderDate(order.getOrderDate());
            orderResponseDto.setNote(order.getNote());
            orderResponseDtos.add(orderResponseDto);
        }
        return orderResponseDtos;
    }
}
