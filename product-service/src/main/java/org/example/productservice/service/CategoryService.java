package org.example.productservice.service;

import org.example.productservice.dto.CategoryResponseDto;
import org.example.productservice.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    Optional<CategoryResponseDto> get(Long id);
    List<CategoryResponseDto> search(String name);
    List<CategoryResponseDto> getAll();
    Category convertToEntity(CategoryResponseDto categoryResponseDTO);
    CategoryResponseDto convertToDTO(Category category);
}
