package org.example.orderservice.service;

import org.example.orderservice.dto.*;
import org.example.orderservice.model.Order;
import org.example.orderservice.model.OrderProduct;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                                        orderProductDto.setSellingPrice(orderProduct.getSellingPrice());
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
                                    orderProductDto.setSellingPrice(orderProduct.getSellingPrice());
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
        Order order = new Order();
        order.setUserId(orderCreateDto.getUser().getID());
        order.setTotal(orderCreateDto.getTotal());
        order.setOrderDate(LocalDateTime.now());
        order.setNote(orderCreateDto.getNote());
        order.setOrderProducts(orderCreateDto.getProducts()
                .stream()
                .map(
                    orderProductDto -> {
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setSellingPrice(orderProductDto.getSellingPrice());
                        orderProduct.setQuantity(orderProductDto.getQuantity());
                        orderProduct.setProductId(orderProductDto.getProduct().getId());
                        orderProduct.setOrder(order);
                        return orderProduct;
                    }
                ).toList()
        );
        orderRepository.save(order);
        return true;
    }

    @Override
    public ProductStatDto getProductStat(LocalDateTime start, LocalDateTime end) {
        ProductStatDto productStatDto = new ProductStatDto();
        productStatDto.setTotalRevenue(orderRepository.calculateTotalRevenue(start, end));
        productStatDto.setQuantitySold(orderRepository.countProductSold(start, end));
        productStatDto.setOrders(getOrdersByDate(start, end));
        long total = 0;
        List<ProductResponseDto> products = new ArrayList<>();
        for (OrderResponseDto order : productStatDto.getOrders()) {
            for (OrderProductDto orderProduct : order.getProducts()) {
                products.add(orderProduct.getProduct());
                total += orderProduct.getProduct().getPurchasePrice() * orderProduct.getQuantity();
            }
        }
        productStatDto.setProducts(products);
        productStatDto.setTotalProfit(productStatDto.getTotalRevenue() - total);
        System.out.println(productStatDto);
        return productStatDto;
    }

    @Override
    public List<OrderResponseDto> getOrdersByDate(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByOrderDateBetween(start, end)
                .stream()
                .map(order -> {
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
                                    orderProductDto.setSellingPrice(orderProduct.getSellingPrice());
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
    public List<OrderResponseDto> getTop10HighestOrderValue() {
        return orderRepository.getTop10HighestOrderValue(PageRequest.of(0, 10))
                .stream()
                .map(order -> {
                    OrderResponseDto orderResponseDto = new OrderResponseDto();
                    orderResponseDto.setId(order.getId());
                    UserResponseDto userResponseDto = new UserResponseDto();
                    userResponseDto.setID(order.getUserId());
                    orderResponseDto.setUser(fetchUser(userResponseDto));
                    orderResponseDto.setProducts(order.getOrderProducts()
                            .stream()
                            .map(
                                orderProduct -> {
                                    System.out.println(order.getId());
                                    OrderProductDto orderProductDto = new OrderProductDto();
                                    orderProductDto.setId(orderProduct.getId());
                                    orderProductDto.setSellingPrice(orderProduct.getSellingPrice());
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
