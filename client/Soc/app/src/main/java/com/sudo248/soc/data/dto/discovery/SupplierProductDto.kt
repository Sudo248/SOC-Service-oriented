package com.sudo248.soc.data.dto.discovery

data class SupplierProductDto(
    val supplierId: Int,
    val productId: Int,
    val distance: Double,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Double,
    val rate: Double,
)
