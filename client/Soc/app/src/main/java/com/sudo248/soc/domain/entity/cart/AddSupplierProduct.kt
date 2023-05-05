package com.sudo248.soc.domain.entity.cart

data class AddSupplierProduct(
    val supplierId: String,
    val productId: String,
    val amount: Int
)
