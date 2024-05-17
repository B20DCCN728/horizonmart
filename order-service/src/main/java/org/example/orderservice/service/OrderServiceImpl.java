package org.example.orderservice.service;

import org.example.orderservice.RestTemplateConfig;
import org.example.orderservice.dto.*;
import org.example.orderservice.model.Order;
import org.example.orderservice.model.OrderProduct;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Optional<OrderResponseDto> get(Long id) {
        return orderRepository.findById(id)
                .map(
                    order -> {
                        OrderResponseDto orderResponseDto = new OrderResponseDto();
                        orderResponseDto.setId(order.getId());
                        UserResponseDto userResponseDto = new UserResponseDto();
                        userResponseDto.setID(order.getUserId());
                        orderResponseDto.setUser(fetchUser(userResponseDto));
                        orderResponseDto.setProducts(order.getOrderProducts()
                                .stream()
                                .map(
                                    orderProduct -> {
                                        OrderProductDto orderProductDto = new OrderProductDto();
                                        orderProductDto.setId(orderProduct.getId());
                                        orderProductDto.setQuantity(orderProduct.getQuantity());
                                        ProductResponseDto productResponseDto = new ProductResponseDto();
                                        productResponseDto.setId(orderProduct.getProductId());
                                        orderProductDto.setProduct(fetchProduct(productResponseDto));
                                        return orderProductDto;
                                    }
                                ).toList()
                        );
                        orderResponseDto.setTotal(order.getTotal());
                        orderResponseDto.setOrderDate(order.getOrderDate());
                        orderResponseDto.setNote(order.getNote());
                        return orderResponseDto;
                    }
                );
    }
    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    // Create an OrderResponseDto object
                    OrderResponseDto orderResponseDto = new OrderResponseDto();
                    // Set the ID of the order
                    orderResponseDto.setId(order.getId());
                    // Create a UserResponseDto object
                    UserResponseDto userResponseDto = new UserResponseDto();
                    // Set the ID of the order
                    userResponseDto.setID(order.getUserId());
                    // Set the user of the order
                    orderResponseDto.setUser(fetchUser(userResponseDto));
                    // Set a list of OrderProductDto objects
                    orderResponseDto.setProducts(order.getOrderProducts()
                            .stream()
                            .map(
                                orderProduct -> {
                                    OrderProductDto orderProductDto = new OrderProductDto();
                                    orderProductDto.setId(orderProduct.getId());
                                    orderProductDto.setQuantity(orderProduct.getQuantity());
                                    ProductResponseDto productResponseDto = new ProductResponseDto();
                                    productResponseDto.setId(orderProduct.getProductId());
                                    orderProductDto.setProduct(fetchProduct(productResponseDto));
                                    return orderProductDto;
                                }
                            ).toList()
                    );
                    orderResponseDto.setTotal(order.getTotal());
                    orderResponseDto.setOrderDate(order.getOrderDate());
                    orderResponseDto.setNote(order.getNote());
                    return orderResponseDto;
                }).toList();
    }

    @Override
    public Boolean create(OrderCreateDto orderCreateDto) {
        return null;
    }

    // Fetch product by ID
    private ProductResponseDto fetchProduct(ProductResponseDto productResponseDto) {
        return restTemplate.getForObject(
                "http://product-service/product/get/" + productResponseDto.getId(),
                ProductResponseDto.class
        );
    }

    // Fetch products
    private List<ProductResponseDto> fetchProducts(List<ProductResponseDto> products) {
        return products.stream()
                .map(this::fetchProduct)
                .toList();
    }

    // Fetch user by ID
    private UserResponseDto fetchUser(UserResponseDto userResponseDto) {
        return restTemplate.getForObject(
                "http://user-service/user/get/" + userResponseDto.getID(),
                UserResponseDto.class
        );
    }
}
