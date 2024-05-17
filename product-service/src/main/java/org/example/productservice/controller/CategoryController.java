package org.example.productservice.controller;

import org.example.productservice.dto.CategoryResponseDto;
import org.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Get all categories
    @GetMapping("/get-all")
    public List<CategoryResponseDto> getAll() throws InterruptedException {
        Thread.sleep(1000);
        return categoryService.getAll();
    }

    // Search category by input key
    @GetMapping("/search/{key}")
    public List<CategoryResponseDto> search(@PathVariable String key) throws InterruptedException {
        Thread.sleep(1000);
        return categoryService.search(key);
    }

    // Get specific category by id
    @GetMapping("/get/{id}")
    public CategoryResponseDto getCategory(@PathVariable Long id) {
        return categoryService.get(id).orElse(null);
    }
}
