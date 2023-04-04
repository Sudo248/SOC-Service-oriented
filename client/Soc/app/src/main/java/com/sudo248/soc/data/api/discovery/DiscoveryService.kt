package com.sudo248.soc.data.api.discovery

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.dto.discovery.CategoryDto
import com.sudo248.soc.data.dto.discovery.ProductDto
import com.sudo248.soc.domain.common.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@ApiService(baseUrl = BuildConfig.BASE_URL + "discovery/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface DiscoveryService {
    @GET("category")
    suspend fun getAllCategory(): Response<BaseResponse<List<CategoryDto>>>

    @GET("category/{categoryId}")
    suspend fun getCategoryById(@Path("categoryId") categoryId: String): Response<BaseResponse<CategoryDto>>

    @GET("product/{productId}")
    suspend fun getProductById(@Path("productId") productId: String): Response<BaseResponse<ProductDto>>
}