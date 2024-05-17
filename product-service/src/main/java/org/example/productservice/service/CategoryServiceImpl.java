package org.example.productservice.service;


import org.example.productservice.dto.CategoryResponseDto;
import org.example.productservice.model.Category;
import org.example.productservice.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Optional<CategoryResponseDto> get(Long id) {
        return categoryRepository.findById(id).map(
                category -> new CategoryResponseDto(
                        category.getId(),
                        category.getName(),
                        category.getDescription()
                )
        );
    }

    @Override
    public List<CategoryResponseDto> search(String name) {
        return categoryRepository.findByNameContaining(name).stream()
                .map(
                        category -> new CategoryResponseDto(
                                category.getId(),
                                category.getName(),
                                category.getDescription()
                        ))
                .toList();
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(
                        category -> new CategoryResponseDto(
                                category.getId(),
                                category.getName(),
                                category.getDescription()
                        ))
                .toList();
    }

    @Override
    public Category convertToEntity(CategoryResponseDto categoryResponseDTO) {
        return modelMapper.map(categoryResponseDTO, Category.class);
    }

    @Override
    public CategoryResponseDto convertToDTO(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }
}
