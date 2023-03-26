package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.repository.CategoryRepository;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.discoveryservice.service.ProductService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryProductService categoryProductService;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category savedCategory = categoryRepository.save(toEntity(categoryDto));
        categoryDto.setCategoryId(savedCategory.getCategoryId());
        return categoryDto;
    }
    public CategoryDto getCategoryById(int id){
        List<Category> categories = categoryRepository.findAll();
        for(Category c: categories){
            if(c.getCategoryId() == id){
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
        categoryDto.setImage(c.getImage());
        categoryDto.setSupplierId(c.getSupplierId());
        categoryDto.setProducts(categoryProductService.getProductByIdCategory(c.getCategoryId()));
        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());
        category.setImage(categoryDto.getImage());
        category.setSupplierId(categoryDto.getSupplierId());
        return category;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDto categoryDto = toDto(category);
            return categoryDto;
        }).collect(Collectors.toList());
    }
}
