package com.sudo248.soc.data.dto.cart

data class AddSupplierProductDto(
    val supplierId: String,
    val productId: String,
    val amount: Int
)
