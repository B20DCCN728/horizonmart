package org.example.orderservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long quantity;
    private Long purchasePrice;
    private Long sellingPrice;
    private LocalDateTime createdDate;
    private String imagePath;
    private String description;
    private CategoryResponseDto category;
    private SupplierResponseDto supplier;
}
