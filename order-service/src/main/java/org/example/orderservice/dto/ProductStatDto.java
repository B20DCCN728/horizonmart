package org.example.orderservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatDto {
    private long totalRevenue;
    private long totalProfit;
    private long quantitySold;
    List<OrderResponseDto> orders;
    List<ProductResponseDto> products;
}
