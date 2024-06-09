package org.example.productservice.repository;

import org.example.productservice.model.Category;
import org.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String keyword);
    Boolean existsByName(String name);
    List<Product> findAllByCategory(Category category);
}
