package com.sudo248.imageservice.service.impl;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.imageservice.controller.dto.ImageDto;
import com.sudo248.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ImageServiceImpl implements ImageService {

    @Value("${store.image.path}")
    private String storeImagePath;

    @Override
    public ResponseEntity<BaseResponse<?>> storeImage(MultipartFile image) {
        return handleException(() -> {
            String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                InputStream inputStream = image.getInputStream();
                Path storePath = Paths.get(storeImagePath);
                Path imagePath = storePath.resolve(imageName);
                Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + imageName, ioe);
            }
            ImageDto imageDto = new ImageDto(imageName);
            return BaseResponse.ok(imageDto);
        });
    }
}
