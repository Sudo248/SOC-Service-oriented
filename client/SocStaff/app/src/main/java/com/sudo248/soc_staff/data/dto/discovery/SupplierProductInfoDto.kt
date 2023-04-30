package com.sudo248.soc_staff.data.dto.discovery

data class SupplierProductInfo(
    val supplierId: String,
    val productId: String,
    val categoryId: String,
    val productImages: List<ImageDto>,
    val productName: String,
    val categoryName: String,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Int,
    val rate: Double,
)
