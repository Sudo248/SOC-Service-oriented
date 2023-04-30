package com.sudo248.soc_staff.domain.entity.discovery

import com.sudo248.base_android.base.ItemDiff

data class CategoryInfo(
    val categoryId: String = "",
    val name: String,
    val image: String,
    val supplierId: String? = null,
) : ItemDiff, java.io.Serializable {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return other == this
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return other == this
    }
}