package com.sudo248.soc.data.dto.cart

data class CartDto(
    val cartId: String,
    val totalPrice: Double,
    val totalAmount: Int,
    val status: String,
    val cartSupplierProducts: List<CartSupplierProductDto>
)
