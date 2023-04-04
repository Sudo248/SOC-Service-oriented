package com.sudo248.soc.data.dto.discovery

data class ProductDto(
    val productId: Int,
    val name: String,
    val description: String,
    val sku: String,
    val images: List<ImageDto>?,
    val supplierProducts: List<SupplierProductDto>,
)