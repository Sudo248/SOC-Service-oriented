package com.sudo248.soc.data.dto.cart

import com.sudo248.soc.data.dto.discovery.ProductDto
import com.sudo248.soc.data.dto.discovery.SupplierDto
import com.sudo248.soc.domain.entity.discovery.Route

data class SupplierProductDetailDto(
    val supplier: SupplierDto,
    val product: ProductDto,
    val route: Route = Route(),
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Double,
    val rate: Double,
)