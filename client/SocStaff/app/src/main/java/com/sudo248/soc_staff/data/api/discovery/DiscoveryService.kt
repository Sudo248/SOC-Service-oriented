package com.sudo248.soc_staff.data.api.discovery

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc_staff.BuildConfig
import com.sudo248.soc_staff.data.api.BaseResponse
import com.sudo248.soc_staff.data.dto.discovery.*
import com.sudo248.soc_staff.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

@ApiService(baseUrl = BuildConfig.BASE_URL + "discovery/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface DiscoveryService {

    @GET("category/info")
    suspend fun getAllCategoryInfo(): Response<BaseResponse<List<CategoryInfoDto>>>

    @POST("category")
    suspend fun postCategory(@Body categoryDto: CategoryDto): Response<BaseResponse<CategoryDto>>

    @PUT("category")
    suspend fun putCategory(@Body categoryDto: CategoryDto): Response<BaseResponse<CategoryDto>>

    @GET("category")
    suspend fun getAllCategory(): Response<BaseResponse<List<CategoryDto>>>

    @GET("category/{categoryId}")
    suspend fun getCategoryById(@Path("categoryId") categoryId: String): Response<BaseResponse<CategoryDto>>

    @POST("product")
    suspend fun postProduct(@Body productDto: ProductDto): Response<BaseResponse<ProductDto>>

    @PUT("product")
    suspend fun putProduct(@Body productDto: ProductDto): Response<BaseResponse<ProductDto>>

    @GET("product/{productId}")
    suspend fun getProductById(@Path("productId") productId: String): Response<BaseResponse<ProductDto>>

    @GET("suppliers/products")
    suspend fun getSupplierProductInfo(): Response<BaseResponse<List<SupplierProductInfoDto>>>

    @POST("supplier")
    suspend fun postSupplier(@Body supplierDto: SupplierDto): Response<BaseResponse<SupplierDto>>

    @GET("supplier/user")
    suspend fun getSupplier(@Query("isDetail") isDetail: Boolean = false): Response<BaseResponse<SupplierDto>>
}