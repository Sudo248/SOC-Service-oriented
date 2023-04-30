package com.sudo248.soc_staff.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc.domain.entity.discovery.Category
import com.sudo248.soc.domain.entity.discovery.Product

interface DiscoveryRepository {

    suspend fun getAllCategory(): DataState<List<Category>, Exception>

    suspend fun getCategoryById(categoryId: String): DataState<Category, Exception>

    suspend fun getProductById(productId: String): DataState<Product, Exception>
}