package com.sudo248.soc_staff.data.mapper

import com.sudo248.soc.data.dto.discovery.CategoryDto
import com.sudo248.soc.data.dto.discovery.ProductDto
import com.sudo248.soc.data.dto.discovery.SupplierProductDto
import com.sudo248.soc.domain.entity.discovery.Category
import com.sudo248.soc.domain.entity.discovery.Product
import com.sudo248.soc.domain.entity.discovery.SupplierProduct

fun SupplierProductDto.toSupplierProduct(): SupplierProduct {
    return SupplierProduct(
        supplierId = supplierId,
        productId = productId,
        route = route,
        amountLeft = amountLeft,
        price = price,
        soldAmount = soldAmount,
        rate = rate,
    )
}

fun ProductDto.toProduct(): Product {
    return Product(
        productId = productId,
        name = name,
        description = description,
        sku = sku,
        images = images?.map { it.url } ?: listOf("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fA%3D%3D&w=1000&q=80"),
        supplierProducts = supplierProducts.map { it.toSupplierProduct() }
    )
    //
}

fun CategoryDto.toCategory(): Category {
    return Category(
        categoryId = categoryId,
        name = name,
        imageUrl = image,
        products = products.map { it.toProduct() }
    )
}