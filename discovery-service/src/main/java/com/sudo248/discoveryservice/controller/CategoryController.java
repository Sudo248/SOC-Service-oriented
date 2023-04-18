package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addCategory(@RequestBody CategoryDto categoryDto) {
        return Utils.handleException(() -> {
            CategoryDto savedCategory = categoryService.addCategory(categoryDto);
            return BaseResponse.ok(savedCategory);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllCategories() {
        return Utils.handleException(() -> {
            List<CategoryDto> categories = categoryService.getAllCategories();
            return BaseResponse.ok(categories);
        });
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<BaseResponse<?>> getCategoryById(@PathVariable("categoryId") String categoryId) {
        return Utils.handleException(() -> {
            CategoryDto category = categoryService.getCategoryById(categoryId);
            if (category == null) {
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist category");
            }
            return BaseResponse.ok(category);
        });
    }
}