package org.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDto {
    private Long id;
    private ProductResponseDto product;
    private Long quantity;
    private Long sellingPrice;
}
