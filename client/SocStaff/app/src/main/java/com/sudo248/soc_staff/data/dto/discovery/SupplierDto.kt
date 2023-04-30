package com.sudo248.soc_staff.data.dto.discovery

import com.sudo248.soc_staff.domain.entity.user.Location

data class SupplierDto(
    val supplierId: String = "",
    val name: String,
    val avatar: String,
    val location: Location? = null,
    val supplierProducts: List<SupplierProductDto>? = null
)
