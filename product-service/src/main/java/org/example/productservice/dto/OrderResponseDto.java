package org.example.productservice.dto;

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
    private Long total;
    private LocalDateTime orderDate;
    private String note;
}
