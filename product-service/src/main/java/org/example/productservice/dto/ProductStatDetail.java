package org.example.productservice.dto;


import org.example.productservice.service_server.SupplierResponseDto;

import java.time.LocalDateTime;

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
        super(id, name, purchasePrice, sellingPrice, quantity, createdDate, imagePath, description, category, supplier);
        this.totalRevenue = totalRevenue;
        this.totalProfit = totalProfit;
        this.quantitySold = quantitySold;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(long totalProfit) {
        this.totalProfit = totalProfit;
    }

    public long getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(long quantitySold) {
        this.quantitySold = quantitySold;
    }
}
