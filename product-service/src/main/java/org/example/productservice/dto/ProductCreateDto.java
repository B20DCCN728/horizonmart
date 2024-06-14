package org.example.productservice.dto;

import lombok.*;
import org.example.productservice.service_server.SupplierResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCreateDto {
    private SupplierResponseDto supplier;
    private CategoryResponseDto category;

    private String name;
    private Long quantity;
    private Long purchasePrice;
    private Long sellingPrice;
    private LocalDateTime createdDate;
    private String imagePath;
    private String description;
}
