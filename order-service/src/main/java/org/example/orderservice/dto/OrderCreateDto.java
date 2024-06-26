package org.example.orderservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateDto {
    private UserResponseDto user;
    private List<OrderProductDto> products;
    private int total;
    private String note;
}
