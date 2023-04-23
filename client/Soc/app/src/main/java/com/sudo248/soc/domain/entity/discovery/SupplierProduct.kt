package com.sudo248.soc.domain.entity.discovery

data class SupplierProduct(
    val supplierId: String,
    val productId: String,
    val route: Route,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Int,
    val rate: Double,
)