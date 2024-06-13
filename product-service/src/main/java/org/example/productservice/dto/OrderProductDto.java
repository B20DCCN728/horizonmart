package org.example.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDto {
    private Long id;
    private ProductResponseDto product;
    private int quantity;
    private int sellingPrice;
}
