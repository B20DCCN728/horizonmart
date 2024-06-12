package org.example.productservice.dto;

import lombok.*;
import org.example.productservice.service_server.Order;

import java.util.List;

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
