package com.sudo248.soc_staff.data.mapper

import android.util.Log
import com.sudo248.soc_staff.data.dto.discovery.*
import com.sudo248.soc_staff.domain.entity.discovery.*

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
        categoryId = categoryIds[0],
        sku = sku,
        images = images?.map { it.url } ?: listOf("product_default.png"),
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

fun Product.toProductDto(): ProductDto {
    return ProductDto(
        productId = productId,
        name = name,
        description = description,
        sku = sku,
        images = images.map { ImageDto(url = it) },
        categoryIds = listOf(categoryId),
        supplierProducts = supplierProducts.map { it.toSupplerProductDto() }
    )
}

fun Category.toCategoryDto(): CategoryDto {
    return CategoryDto(
        categoryId = categoryId,
        name = name,
        image = imageUrl
    )
}

fun Supplier.toSupplierDto(): SupplierDto {
    return SupplierDto(
        supplierId,
        name,
        avatar,
        location
    )
}

fun SupplierDto.toSupplier(): Supplier {
    return Supplier(
        supplierId, name, avatar, location
    )
}

fun CategoryInfoDto.toCategoryInfo(): CategoryInfo {
    return CategoryInfo(
        categoryId, name, image, supplierId
    )
}

fun SupplierProductInfoDto.toSupplierProductInfo(): SupplierProductInfo {
    return SupplierProductInfo(
        supplierId,
        productId,
        categoryId,
        productImages.map { it.url },
        description,
        productName,
        categoryName,
        amountLeft,
        price,
        soldAmount,
        rate,
        sku
    )
}

fun SupplierProduct.toSupplerProductDto(): SupplierProductDto {
    return SupplierProductDto(
        supplierId = supplierId,
        productId = productId,
        route = route,
        amountLeft = amountLeft,
        price = price,
        soldAmount = soldAmount,
        rate = rate
    )
}