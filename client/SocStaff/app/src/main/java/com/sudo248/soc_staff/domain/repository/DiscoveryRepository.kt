package com.sudo248.soc_staff.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc_staff.domain.entity.discovery.*

interface DiscoveryRepository {

    suspend fun getAllCategory(): DataState<List<Category>, Exception>

    suspend fun getCategoryById(categoryId: String): DataState<Category, Exception>

    suspend fun getProductById(productId: String): DataState<Product, Exception>

    suspend fun postSupplier(supplier: Supplier): DataState<Supplier, Exception>

    suspend fun postCategory(category: Category): DataState<Category, Exception>

    suspend fun putCategory(category: Category): DataState<Category, Exception>

    suspend fun postProduct(product: Product): DataState<Product, Exception>

    suspend fun putProduct(product: Product): DataState<Product, Exception>

    suspend fun getCategoryInfo(): DataState<List<CategoryInfo>, Exception>

    suspend fun getSupplierProductInfo(): DataState<List<SupplierProductInfo>, Exception>

    suspend fun getSupplier(): DataState<Supplier, Exception>
}