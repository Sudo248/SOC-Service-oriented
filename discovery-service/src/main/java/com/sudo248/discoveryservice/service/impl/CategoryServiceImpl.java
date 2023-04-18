package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.repository.CategoryRepository;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.repository.entity.CategoryProduct;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.discoveryservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryProductService categoryProductService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryProductService categoryProductService) {
        this.categoryRepository = categoryRepository;
        this.categoryProductService = categoryProductService;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(toEntity(categoryDto));
        categoryDto.setCategoryId(savedCategory.getCategoryId());
        return categoryDto;
    }
    public CategoryDto getCategoryById(String categoryId){
        List<Category> categories = categoryRepository.findAll();
        for(Category c: categories){
            if(c.getCategoryId().equals(categoryId)){
                return toDto(c);
            }
        }
        return null;
    }

    @Override
    public CategoryDto toDto(Category c) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(c.getCategoryId());
        categoryDto.setName(c.getName());
        categoryDto.setImageUrl(c.getImageUrl());
        categoryDto.setSupplierId(c.getSupplierId());
        c.getCategoryProducts().stream().map(CategoryProduct::getProduct).collect(Collectors.toList());
        categoryDto.setProducts(categoryProductService.getProductByIdCategory(c.getCategoryId()));
        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());
        category.setImageUrl(categoryDto.getImageUrl());
        category.setSupplierId(categoryDto.getSupplierId());
        return category;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }
}
