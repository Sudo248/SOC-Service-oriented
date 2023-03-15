package com.sudo248.soc.ui.uimodel

import androidx.databinding.ObservableField
import com.sudo248.base_android.base.ItemDiff


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 12:31 - 12/03/2023
 */
data class CategoryUiModel(
    val categoryId: String,
    val imageUrl: ObservableField<String> = ObservableField(""),
    val name: ObservableField<String> = ObservableField("")
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return this.categoryId == (other as CategoryUiModel).categoryId
    }
}