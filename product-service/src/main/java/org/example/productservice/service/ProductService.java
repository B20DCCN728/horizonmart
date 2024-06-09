package org.example.productservice.service;

import org.example.productservice.dto.CategoryResponseDto;
import org.example.productservice.dto.ProductCreateDto;
import org.example.productservice.dto.ProductResponseDto;
import org.example.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Boolean create(ProductCreateDto product);
    Boolean update(ProductCreateDto product);
    Boolean delete(Product product);
    Optional<ProductResponseDto> get(Long id);
    List<ProductResponseDto> search(String key);
    List<ProductResponseDto> getAll();
    List<ProductResponseDto> getProductByCategory(CategoryResponseDto category);
    Long getTotalRevenue();
    Long getProductProfit();
    Long getTotalProductSold();
    List<Product> getProductRevenue();
    Long getTotalRevenueByDate(LocalDateTime start, LocalDateTime end);
    Optional<Product> getMostRevenueProduct();
}
