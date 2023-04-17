package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.ImageService;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/discovery")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public ResponseEntity<BaseResponse<?>> getAllImages() {
        List<ImageDto> images = imageService.getAllImages();
        return BaseResponse.ok(images);
    }

    @PostMapping("/product/addImage/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> addProductImage(@RequestBody ImageDto imageDto,@PathVariable String id) {
        ProductDto product = imageService.addProductImageUrl(imageDto,id);
        return BaseResponse.ok(product);
    }
    @GetMapping("/product/{id}/images")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getProductImage(@PathVariable String id) {
        List<ImageDto> imageDtos = imageService.getProductImageById(id);
        return BaseResponse.ok(imageDtos);
    }
}
