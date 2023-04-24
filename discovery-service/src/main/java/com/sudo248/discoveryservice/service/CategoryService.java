package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(String id);
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);

}