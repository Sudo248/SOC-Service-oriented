package com.sudo248.soc.domain.entity.discovery

data class SupplierProduct(
    val supplierId: String,
    val productId: String,
    val distance: Double,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Double,
    val rate: Double,
    val location: String,
    val timeDelivery: Long,
)
