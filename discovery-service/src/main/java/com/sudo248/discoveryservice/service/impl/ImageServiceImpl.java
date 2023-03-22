package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.ImageRepository;
import com.sudo248.discoveryservice.repository.ProductRepository;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.repository.entity.Image;
import com.sudo248.discoveryservice.repository.entity.Product;
import com.sudo248.discoveryservice.service.ImageService;
import com.sudo248.discoveryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository ProductRepository;
    @Override
    public List<ImageDto> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(image -> {
            ImageDto imageDto = new ImageDto();
            imageDto.setImageId(image.getImageId());
            imageDto.setUrl(image.getUrl());
            imageDto.setOwnerId(image.getOwnerId());
            return imageDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ImageDto> getProductImageById(int id) {
        Optional<Product> p =  ProductRepository.findById(id);
        List<Image> pImages = p.get().getImages();
        List<ImageDto> imageDtos = new ArrayList<>();
        for(Image i: pImages){
            ImageDto imageDto1 = new ImageDto(i.getImageId(),i.getUrl(),i.getOwnerId());
            imageDtos.add(imageDto1);
        }
        return imageDtos;
    }
    @Override
    public ProductDto addProductImageUrl(ImageDto imageDto, int id){
        Product product = new Product();
        Optional<Product> p =  ProductRepository.findById(id);
        Image image = new Image(imageDto.getImageId(), imageDto.getUrl(), imageDto.getOwnerId(), product);
        List<Image> pImages = p.get().getImages();
        pImages.add(image);
        product.setProductId(id);
        product.setDescription(p.get().getDescription());
        product.setName(p.get().getName());
        product.setSku(p.get().getSku());
        product.setImages(p.get().getImages());
        Product savedProduct = ProductRepository.save(product);
        List<ImageDto> imageDtos = new ArrayList<>();
        for(Image i: pImages){
            ImageDto imageDto1 = new ImageDto(i.getImageId(),i.getUrl(),i.getOwnerId());
            imageDtos.add(imageDto1);
        }
        ProductDto productDto = new ProductDto(savedProduct.getProductId(), savedProduct.getName(),savedProduct.getDescription(),
                savedProduct.getSku(), imageDtos);
        return productDto;
    }

    @Override
    public ImageDto toDto(Image image) {
        return null;
    }

    @Override
    public Image toEntity(ImageDto imageDto) {
        return null;
    }
}
