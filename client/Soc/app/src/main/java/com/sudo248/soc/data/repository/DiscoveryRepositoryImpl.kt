package com.sudo248.soc.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.state
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc.data.api.discovery.DiscoveryService
import com.sudo248.soc.data.ktx.data
import com.sudo248.soc.data.ktx.errorBody
import com.sudo248.soc.data.mapper.toCategory
import com.sudo248.soc.data.mapper.toProduct
import com.sudo248.soc.domain.entity.discovery.Category
import com.sudo248.soc.domain.entity.discovery.Product
import com.sudo248.soc.domain.repository.DiscoveryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiscoveryRepositoryImpl @Inject constructor(
    private val discoveryService: DiscoveryService,
    private val ioDispatcher: CoroutineDispatcher
) : DiscoveryRepository {
    override suspend fun getAllCategory(): DataState<List<Category>, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getAllCategory())
        response.data().map {
            async { it.toCategory() }
        }.awaitAll()
    }

    override suspend fun getCategoryById(categoryId: String): DataState<Category, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getCategoryById(categoryId))
        response.data().toCategory()
    }

    override suspend fun getProductById(productId: String): DataState<Product, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getProductById(productId))
        response.data().toProduct()
    }
}