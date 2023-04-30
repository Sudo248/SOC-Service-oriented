package com.sudo248.soc_staff.data.dto.discovery

data class CategoryInfoDto(
    val categoryId: String,
    val name: String,
    val image: String,
    val supplierId: String? = null,
)
