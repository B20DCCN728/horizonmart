package org.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatDto {
    private int totalRevenue;
    private int totalProfit;
    private int quantitySold;
}
