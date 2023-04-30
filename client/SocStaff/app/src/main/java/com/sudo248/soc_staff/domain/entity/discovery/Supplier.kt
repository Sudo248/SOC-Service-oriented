package com.sudo248.soc_staff.domain.entity.discovery

import com.sudo248.soc_staff.domain.entity.user.Location

data class Supplier(
    val supplierId: String = "",
    val name: String,
    val avatar: String,
    val location: Location? = null,
    val supplierProducts: List<SupplierProduct>? = null
)
