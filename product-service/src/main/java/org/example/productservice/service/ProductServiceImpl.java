package org.example.productservice.service;

import jakarta.validation.constraints.NotNull;
import org.example.productservice.dto.ProductCreateDto;
import org.example.productservice.dto.ProductResponseDto;
import org.example.productservice.model.Category;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.example.productservice.service_server.SupplierResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Boolean create(ProductCreateDto product) {
        if (productRepository.existsByName(product.getName()))
            return false;
        return getaBoolean(product);
    }

    @Override
    public Boolean update(ProductCreateDto product) {
        return getaBoolean(product);
    }

    @NotNull
    private Boolean getaBoolean(ProductCreateDto product) {
        Product upProduct = new Product();
        upProduct.setName(product.getName());
        upProduct.setPurchasePrice(product.getPurchasePrice());
        upProduct.setSellingPrice(product.getSellingPrice());
        upProduct.setCreatedDate(LocalDateTime.now());
        upProduct.setImagePath(product.getImagePath());
        upProduct.setDescription(product.getDescription());
        Category category = new Category();
        category.setId(product.getCategory().getId());
        upProduct.setCategory(category);
        upProduct.setSupplierID(product.getSupplier().getId());
        productRepository.save(upProduct);
        return true;
    }

    @Override
    public Boolean delete(Product product) {
        productRepository.delete(product);
        return true;
    }

    @Override
    public Optional<ProductResponseDto> get(Long id) {
        return productRepository.findById(id)
                .map(
                        product -> {
                            String supplierURL = "http://supplier-service/supplier/get/" + product.getSupplierID();
                            SupplierResponseDto supplier = restTemplate.getForObject(supplierURL, SupplierResponseDto.class);
                            return new ProductResponseDto(
                                    product.getId(),
                                    product.getName(),
                                    product.getPurchasePrice(),
                                    product.getSellingPrice(),
                                    product.getCreatedDate(),
                                    product.getImagePath(),
                                    product.getDescription(),
                                    product.getCategory(),
                                    supplier
                            );
                        }
                );
    }

    @Override
    public List<ProductResponseDto> search(String key) {
        return productRepository.findByNameContaining(key)
                .stream()
                .map(
                        product -> {
                            String supplierURL = "http://supplier-service/supplier/get/" + product.getSupplierID();
                            SupplierResponseDto supplier = restTemplate.getForObject(supplierURL, SupplierResponseDto.class);
                            return new ProductResponseDto(
                                    product.getId(),
                                    product.getName(),
                                    product.getPurchasePrice(),
                                    product.getSellingPrice(),
                                    product.getCreatedDate(),
                                    product.getImagePath(),
                                    product.getDescription(),
                                    product.getCategory(),
                                    supplier
                            );
                        }
                ).toList();
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    String supplierURL = "http://supplier-service/supplier/get/" + product.getSupplierID();
                    SupplierResponseDto supplier = restTemplate.getForObject(supplierURL, SupplierResponseDto.class);
                    return new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getPurchasePrice(),
                            product.getSellingPrice(),
                            product.getCreatedDate(),
                            product.getImagePath(),
                            product.getDescription(),
                            product.getCategory(),
                            supplier
                    );
                }).toList();
    }

    @Override
    public Long getTotalRevenue() {
        return null;
    }

    @Override
    public Long getProductProfit() {
        return null;
    }

    @Override
    public Long getTotalProductSold() {
        return null;
    }

    @Override
    public List<Product> getProductRevenue() {
        return null;
    }

    @Override
    public Long getTotalRevenueByDate(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Optional<Product> getMostRevenueProduct() {
        return null;
    }
}
