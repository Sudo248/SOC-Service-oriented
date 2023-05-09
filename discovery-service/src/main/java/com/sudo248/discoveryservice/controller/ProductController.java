package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.cache.CacheLocationManager;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    private final CacheLocationManager cacheLocationManager;

    public ProductController(ProductService productService, CacheLocationManager cacheLocationManager) {
        this.productService = productService;
        this.cacheLocationManager = cacheLocationManager;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody ProductDto productDto
    ) {
        return Utils.handleException(() -> {
            ProductDto savedProduct = productService.addProduct(userId, productDto);
            return BaseResponse.ok(savedProduct);
        });
    }

    @PutMapping
    public ResponseEntity<BaseResponse<?>> putProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody ProductDto productDto
    ) {
        return Utils.handleException(() -> {
            ProductDto savedProduct = productService.putProduct(userId, productDto);
            return BaseResponse.ok(savedProduct);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllProducts(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestParam(name = "location", required = false,defaultValue = "") String location
    ) {
        return Utils.handleException(() -> {
            cacheLocationManager.saveLocation(userId, location);
            List<ProductDto> products = productService.getAllProducts(userId);
            return BaseResponse.ok(products);
        });
    }

    @GetMapping("/{productId}")
    public ResponseEntity<BaseResponse<?>> getProductById(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("productId") String productId,
            @RequestParam(name = "location", required = false, defaultValue = "") String location
    ) {
        return Utils.handleException(() -> {
            cacheLocationManager.saveLocation(userId, location);
            ProductDto product = productService.getProductById(userId, productId);
            if (product == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return BaseResponse.ok(product);
        });
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<BaseResponse<?>> getSearchProductsByName(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("productName") String productName
    ) {
        return Utils.handleException(() -> {
            List<ProductDto> products = productService.getProductsByName(userId, productName);
            return BaseResponse.ok(products);
        });
    }

    @GetMapping("/share/{productId}")
    public ResponseEntity<byte[]> getShareLinkProduct(
            @PathVariable("productId") String productId
    ) throws IOException {
        String imageUrl = productService.getProductImageById(productId);
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        byte[] bytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}