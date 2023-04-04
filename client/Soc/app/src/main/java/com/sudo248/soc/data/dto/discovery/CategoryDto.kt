package com.sudo248.soc.data.dto.discovery

data class CategoryDto(
    val categoryId: Int,
    val name: String,
    val image: String,
    val products: List<ProductDto>,
)
