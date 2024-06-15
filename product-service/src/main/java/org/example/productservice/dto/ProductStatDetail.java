package org.example.productservice.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.productservice.service_server.SupplierResponseDto;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductStatDetail extends ProductResponseDto {
    private long totalRevenue;
    private long totalProfit;
    private long quantitySold;

    public ProductStatDetail() {
    }

    public ProductStatDetail(
            Long id,
            String name,
            Long quantity,
            Long purchasePrice,
            Long sellingPrice,
            LocalDateTime createdDate,
            String imagePath,
            String description,
            CategoryResponseDto category,
            SupplierResponseDto supplier,
            long totalRevenue,
            long totalProfit,
            long quantitySold
    ) {
        super(
                id,
                name,
                quantity,
                purchasePrice,
                sellingPrice,
                createdDate,
                imagePath,
                description,
                category,
                supplier
        );
        this.totalRevenue = totalRevenue;
        this.totalProfit = totalProfit;
        this.quantitySold = quantitySold;
    }

}
