package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.ImageService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public ResponseEntity<BaseResponse<?>> getAllImages() {
        return Utils.handleException(() -> {
            List<ImageDto> images = imageService.getAllImages();
            return BaseResponse.ok(images);
        });
    }

    @PostMapping("/product/addImage/{productId}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> addProductImage(@RequestBody ImageDto imageDto, @PathVariable("productId") String productId) {
        return Utils.handleException(() -> {
            ProductDto product = imageService.addProductImageUrl(imageDto, productId);
            return BaseResponse.ok(product);
        });
    }

    @GetMapping("/product/{productId}/images")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getProductImage(@PathVariable String productId) {
        return Utils.handleException(() -> {
            List<ImageDto> imageDtos = imageService.getProductImageById(productId);
            return BaseResponse.ok(imageDtos);
        });
    }
}
