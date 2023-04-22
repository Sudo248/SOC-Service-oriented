package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.repository.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories(String userId);
    CategoryDto getCategoryById(String userId, String categoryId);
    CategoryDto toDto(String userId, Category category);
    Category toEntity(CategoryDto categoryDto);

}