package com.sudo248.soc_staff.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.data.api.discovery.DiscoveryService
import com.sudo248.soc_staff.data.ktx.data
import com.sudo248.soc_staff.data.ktx.errorBody
import com.sudo248.soc_staff.data.mapper.*
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.entity.discovery.*
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import kotlinx.coroutines.CoroutineDispatcher
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
        if (response.isSuccess) {
            response.data().map {
                async { it.toCategory() }
            }.awaitAll()
        } else {
            throw response.error().errorBody()
        }

    }

    override suspend fun getCategoryById(categoryId: String): DataState<Category, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getCategoryById(categoryId))
        if (response.isSuccess) {
            response.data().toCategory()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getProductById(productId: String): DataState<Product, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getProductById(productId))
        if (response.isSuccess) {
            response.data().toProduct()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun postSupplier(supplier: Supplier): DataState<Supplier, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.postSupplier(supplier.toSupplierDto()))
        if (response.isSuccess) {
            response.data().toSupplier()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun postCategory(category: Category): DataState<Category, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(discoveryService.postCategory(category.toCategoryDto()))
        if (response.isSuccess) {
            response.data().toCategory()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun putCategory(category: Category): DataState<Category, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(discoveryService.postCategory(category.toCategoryDto()))
        if (response.isSuccess) {
            response.data().toCategory()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun postProduct(product: Product): DataState<Product, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.postProduct(product.toProductDto()))
        if (response.isSuccess) {
            response.data().toProduct()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun putProduct(product: Product): DataState<Product, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.putProduct(product.toProductDto()))
        if (response.isSuccess) {
            response.data().toProduct()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getCategoryInfo(): DataState<List<CategoryInfo>, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getAllCategoryInfo())
        if (response.isSuccess) {
            response.data().map { it.toCategoryInfo() }
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getSupplierProductInfo(): DataState<List<SupplierProductInfo>, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(discoveryService.getSupplierProductInfo())
        if (response.isSuccess) {
            response.data().map { it.toSupplierProductInfo() }
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getSupplier(): DataState<Supplier, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(discoveryService.getSupplier())
        if(response.isSuccess) {
            response.data().toSupplier()
        } else {
            throw response.error().errorBody()
        }
    }
}