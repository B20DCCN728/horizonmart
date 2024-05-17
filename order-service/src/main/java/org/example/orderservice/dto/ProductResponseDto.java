package org.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long purchasePrice;
    private Long sellingPrice;
    private LocalDateTime createdDate;
    private String imagePath;
    private String description;
    private CategoryResponseDto category;
    private SupplierResponseDto supplier;
}
