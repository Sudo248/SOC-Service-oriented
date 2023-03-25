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
@RequestMapping("/api/v1/discovery")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @GetMapping("/image")
    public ResponseEntity<BaseResponse<?>> getAllImages() {
        List<ImageDto> images = imageService.getAllImages();
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", images));
    }

    @PostMapping("/product/addImage/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> addProductImage(@RequestBody ImageDto imageDto,@PathVariable int id) {
        ProductDto product = imageService.addProductImageUrl(imageDto,id);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", product));
    }
    @GetMapping("/product/{id}/images")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getProductImage(@PathVariable int id) {
        List<ImageDto> imageDtos = imageService.getProductImageById(id);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", imageDtos));
    }
}
