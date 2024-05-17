package org.example.orderservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponseDto {
    private Long id;
    private UserResponseDto user;
    private List<OrderProductDto> products;
    private int total;
    private LocalDateTime orderDate;
    private String note;
}
