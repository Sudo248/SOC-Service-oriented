package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.Image;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;

public interface ImageService {
    List<ImageDto> getAllImages();
    List<ImageDto> getProductImageById(String id);
    ProductDto addProductImageUrl(ImageDto imageDto, String id);
    ImageDto toDto(Image image);
    Image toEntity(ImageDto imageDto);

}
