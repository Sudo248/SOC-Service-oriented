package com.sudo248.soc.ui.uimodel

import androidx.databinding.ObservableField
import com.sudo248.base_android.base.ItemDiff


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 23:11 - 12/03/2023
 */
data class ProductUiModel(
    val productId: String = "",
    val supplierId: String = "",
    val name: ObservableField<String> = ObservableField(""),
    val description: String = "",
    val sku: String = "",
    val images: ObservableField<List<String>> = ObservableField(listOf()),
    val amountLeft: ObservableField<Int> = ObservableField(0),
    val price: ObservableField<Double> = ObservableField(0.0),
    val soldAmount: ObservableField<Double> = ObservableField(0.0),
    val rate: ObservableField<Double> = ObservableField(0.0),
    val distance: ObservableField<Double> = ObservableField(0.0),
    val location: ObservableField<String> = ObservableField(""),
    val timeDelivery: ObservableField<String> = ObservableField(""),
    var isLike: ObservableField<Boolean> = ObservableField(false),
) : ItemDiff, java.io.Serializable {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        val productOther = other as ProductUiModel
        return this.productId == productOther.productId &&
                this.supplierId == productOther.supplierId &&
                this.hashCode() == productOther.hashCode()
    }
}