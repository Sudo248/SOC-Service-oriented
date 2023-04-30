package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.cache.CacheLocationManager;
import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.controller.dto.CategoryInfoDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    private final CacheLocationManager cacheLocationManager;

    public CategoryController(CategoryService categoryService, CacheLocationManager cacheLocationManager) {
        this.categoryService = categoryService;
        this.cacheLocationManager = cacheLocationManager;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addCategory(@RequestBody CategoryDto categoryDto) {
        return Utils.handleException(() -> {
            CategoryDto savedCategory = categoryService.putCategory(categoryDto);
            return BaseResponse.ok(savedCategory);
        });
    }

    @PutMapping
    public ResponseEntity<BaseResponse<?>> putCategory(@RequestBody CategoryDto categoryDto) {
        return Utils.handleException(() -> {
            CategoryDto savedCategory = categoryService.addCategory(categoryDto);
            return BaseResponse.ok(savedCategory);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllCategories(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestParam(name = "location", required = false,defaultValue = "") String location
    ) {
        return Utils.handleException(() -> {
            cacheLocationManager.saveLocation(userId, location);
            List<CategoryDto> categories = categoryService.getAllCategories(userId);
            return BaseResponse.ok(categories);
        });
    }

    @GetMapping("/info")
    public ResponseEntity<BaseResponse<?>> getAllCategoriesInfo(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> {
            List<CategoryInfoDto> categories = categoryService.getAllCategoriesInfo();
            return BaseResponse.ok(categories);
        });
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<BaseResponse<?>> getCategoryById(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("categoryId") String categoryId,
            @RequestParam(name = "location", required = false, defaultValue = "") String location
    ) {
        return Utils.handleException(() -> {
            cacheLocationManager.saveLocation(userId, location);
            CategoryDto category = categoryService.getCategoryById(userId, categoryId);
            if (category == null) {
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist category");
            }
            return BaseResponse.ok(category);
        });
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<BaseResponse<?>> getProductByCategoryId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("categoryId") String categoryId,
            @RequestParam(name = "location", required = false, defaultValue = "") String location
    ) {
        return Utils.handleException(() -> {
            cacheLocationManager.saveLocation(userId, location);
            List<ProductDto> products = categoryService.getCategoryById(userId, categoryId).getProducts();
            if (products == null) {
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist category");
            }
            return BaseResponse.ok(products);
        });
    }
}