package com.sudo248.soc.ui.mapper

import androidx.databinding.ObservableField
import com.sudo248.soc.domain.entity.discovery.Product
import com.sudo248.soc.ui.uimodel.ProductUiModel
import com.sudo248.soc.ui.util.TimeUtils

fun Product.toListProductUi(): List<ProductUiModel> {
    return supplierProducts.map {
        ProductUiModel(
            productId = productId,
            supplierId = it.supplierId,
            name = ObservableField(name),
            description = description,
            sku = sku,
            route = ObservableField(it.route),
            images = ObservableField(images),
            amountLeft = ObservableField(it.amountLeft),
            price = ObservableField(it.price),
            soldAmount = ObservableField(it.soldAmount),
            rate = ObservableField(it.rate),
            isLike = ObservableField(isLike)
        )
    }
}
