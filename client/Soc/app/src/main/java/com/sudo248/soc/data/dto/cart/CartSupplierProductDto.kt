package com.sudo248.soc.data.dto.cart

data class CartSupplierProductDto(
    val supplierProduct: SupplierProductDetailDto,
    val amount: Int,
    val totalPrice: Double,
    val cartId: String
)
