package com.sudo248.soc.data.dto.discovery

data class CategoryInfoDto(
    val categoryId: String,
    val name: String,
    val image: String,
    val supplierId: String? = null,
)
