package org.example.productservice.service;

import jakarta.validation.constraints.NotNull;
import org.example.productservice.dto.CategoryResponseDto;
import org.example.productservice.dto.ProductCreateDto;
import org.example.productservice.dto.ProductResponseDto;
import org.example.productservice.dto.ProductStatDto;
import org.example.productservice.model.Category;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.example.productservice.service_server.SupplierResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
                            CategoryResponseDto category = new CategoryResponseDto(
                                    product.getCategory().getId(),
                                    product.getCategory().getName(),
                                    product.getCategory().getDescription()
                            );
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
                                    category,
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
                            CategoryResponseDto category = new CategoryResponseDto(
                                    product.getCategory().getId(),
                                    product.getCategory().getName(),
                                    product.getCategory().getDescription()
                            );
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
                                    category,
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
                    CategoryResponseDto category = new CategoryResponseDto(
                            product.getCategory().getId(),
                            product.getCategory().getName(),
                            product.getCategory().getDescription()
                    );
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
                            category,
                            supplier
                    );
                }).toList();
    }

    @Override
    public List<ProductResponseDto> getProductByCategory(CategoryResponseDto category) {
        Category categoryEntity = new Category();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        return productRepository.findAllByCategory(categoryEntity)
                .stream()
                .map(
                        product -> {
                            CategoryResponseDto categoryDto = new CategoryResponseDto(
                                    product.getCategory().getId(),
                                    product.getCategory().getName(),
                                    product.getCategory().getDescription()
                            );
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
                                    categoryDto,
                                    supplier
                            );
                        }
                ).toList();
    }
    @Override
    public ProductStatDto getProductStat(LocalDateTime from, LocalDateTime to) {
        String orderURL = "http://order-service/order/get-total-revenue/" +
                            from.format(dateTimeFormatter) +
                            "/" +
                            to.format(dateTimeFormatter);
        return restTemplate.getForObject(orderURL, ProductStatDto.class);
    }
}
