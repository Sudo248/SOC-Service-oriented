package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.repository.CategoryRepository;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.domain.util.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(toEntity(categoryDto));
        categoryDto.setCategoryId(savedCategory.getCategoryId());
        return categoryDto;
    }

    public CategoryDto getCategoryById(String userId, String categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        return toDto(userId, category);
    }

    @Override
    public CategoryDto toDto(String userId, Category c) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(c.getCategoryId());
        categoryDto.setName(c.getName());
        categoryDto.setImage(c.getImage());
        categoryDto.setSupplierId(c.getSupplierId());
        categoryDto.setProducts(c.getProducts().stream().map((product -> productService.toDto(userId, product))).collect(Collectors.toList()));
        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(Utils.createIdOrElse(categoryDto.getCategoryId()));
        category.setName(categoryDto.getName());
        category.setImage(categoryDto.getImage());
        category.setSupplierId(categoryDto.getSupplierId());
        return category;
    }

    @Override
    public List<CategoryDto> getAllCategories(String userId) {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(c -> toDto(userId, c)).collect(Collectors.toList());
    }
}
