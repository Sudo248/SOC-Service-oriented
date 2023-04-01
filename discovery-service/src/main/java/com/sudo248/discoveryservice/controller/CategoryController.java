package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<BaseResponse<?>> addCategory(@RequestBody CategoryDto categoryDto) {
        return Utils.handleException(() -> {
            CategoryDto savedCategory = categoryService.addCategory(categoryDto);
            return ResponseEntity.ok(new BaseResponse<>(200,true,"Add categories oke", savedCategory));
        });
    }

    @GetMapping("/category")
    public ResponseEntity<BaseResponse<?>> getAllCategories() {
        return Utils.handleException(() -> {
            List<CategoryDto> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new BaseResponse<>(200,true,"Get All categories oke", categories));
        });
    }
    @GetMapping("/category/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getCategoryById(@PathVariable int id) {
        return Utils.handleException(() -> {
            CategoryDto category = categoryService.getCategoryById(id);
            if (category == null) {
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist category");
            }
            return ResponseEntity.ok(new BaseResponse<>(200,true,"Get category by Id oke", category));
        });
    }
}