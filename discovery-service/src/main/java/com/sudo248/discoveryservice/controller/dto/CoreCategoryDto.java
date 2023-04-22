package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreCategoryDto {
    private String categoryId;
    private String  name, image, supplierId;
}
