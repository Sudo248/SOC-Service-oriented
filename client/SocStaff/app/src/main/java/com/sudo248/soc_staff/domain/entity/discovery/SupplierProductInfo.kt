package com.sudo248.soc_staff.domain.entity.discovery

import com.sudo248.base_android.base.ItemDiff
import com.sudo248.soc_staff.data.dto.discovery.ImageDto

data class SupplierProductInfo(
    val supplierId: String,
    val productId: String,
    val categoryId: String,
    val productImages: List<String>,
    val description: String,
    val productName: String,
    val categoryName: String,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Int,
    val rate: Double,
    val sku: String = ""
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return other == this
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return other == this
    }
}
