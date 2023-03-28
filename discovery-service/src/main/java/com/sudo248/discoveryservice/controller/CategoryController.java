package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/discovery")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<BaseResponse<?>> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return BaseResponse.ok(savedCategory);
    }

    @GetMapping("/category")
    public ResponseEntity<BaseResponse<?>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return BaseResponse.ok(categories);
    }
    @GetMapping("/category/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getCategoryById(@PathVariable String id) {
        CategoryDto category = categoryService.getCategoryById(id);
        return BaseResponse.ok(category);
    }
}