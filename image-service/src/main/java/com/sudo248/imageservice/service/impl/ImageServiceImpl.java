package com.sudo248.imageservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
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
import java.util.List;
import java.util.Map;


@Service
public class ImageServiceImpl implements ImageService {

    @Value("${store.image.path}")
    private String storeImagePath;

    @Value("${flickr.apiKey}")
    private String flickrApiKey;

    @Value("${flickr.secret}")
    private String flickrSecret;

    private final Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> storeImage(MultipartFile image, String source) {
        return handleException(() -> {
            String imageName = image.getOriginalFilename();
            try {
                switch (source) {
                    case "soc":
                        imageName = storeImageOnServer(image);
                        break;
                    case "cloudinary":
                        imageName = uploadImageCloudinary(image);
                        break;
                    default:
                        imageName = storeImageOnServer(image);
                }
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + imageName, ioe);
            }
            ImageDto imageDto = new ImageDto(imageName);
            return BaseResponse.ok(imageDto);
        });
    }

    private String uploadImageToFlickr() {
        Flickr flickr = new Flickr(flickrApiKey, flickrSecret, new REST());
        return "";
    }

    private String storeImageOnServer(MultipartFile image) throws IOException {
        String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        InputStream inputStream = image.getInputStream();
        Path storePath = Paths.get(storeImagePath);
        Path imagePath = storePath.resolve(imageName);
        Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    private String uploadImageCloudinary(MultipartFile image) throws IOException {
        String imageName = System.currentTimeMillis() + "_" + getImageName(image.getOriginalFilename());
        Map result = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap(
                "public_id",imageName,
                "folder","Soc"
        ));
        return (String)result.get("secure_url");
    }

    private String getImageName(String originalFileName) {
        return originalFileName.substring(0, originalFileName.lastIndexOf('.'));
    }
}
