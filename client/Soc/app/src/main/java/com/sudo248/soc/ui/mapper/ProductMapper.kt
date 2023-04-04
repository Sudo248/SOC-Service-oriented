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
            images = ObservableField(images),
            amountLeft = ObservableField(it.amountLeft),
            price = ObservableField(it.price),
            soldAmount = ObservableField(it.soldAmount),
            rate = ObservableField(it.rate),
            distance = ObservableField(it.distance),
            location = ObservableField(it.location),
            timeDelivery = ObservableField(TimeUtils.convertTimestampToString(it.timeDelivery)),
            isLike = ObservableField(isLike)
        )
    }
}
