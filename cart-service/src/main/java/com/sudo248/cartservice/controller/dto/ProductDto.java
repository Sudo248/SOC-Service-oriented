package com.sudo248.cartservice.controller.dto;

import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private String productId;
    private String name,description,sku;
    private List<ImageDto> images;
}
