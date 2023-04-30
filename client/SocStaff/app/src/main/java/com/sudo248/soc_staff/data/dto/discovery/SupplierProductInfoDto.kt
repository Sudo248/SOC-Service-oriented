package com.sudo248.soc_staff.data.dto.discovery

data class SupplierProductInfoDto(
    val supplierId: String = "",
    val productId: String = "",
    val categoryId: String = "",
    val productImages: List<ImageDto> = listOf(),
    val description: String = "",
    val productName: String = "",
    val categoryName: String = "",
    val amountLeft: Int = 0,
    val price: Double = 0.0,
    val soldAmount: Int = 0,
    val rate: Double = 0.0,
    val sku: String = "",
)
