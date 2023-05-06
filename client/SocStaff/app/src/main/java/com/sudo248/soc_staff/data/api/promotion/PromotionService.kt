package com.sudo248.soc_staff.data.api.promotion

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc_staff.BuildConfig
import com.sudo248.soc_staff.data.api.BaseResponse
import com.sudo248.soc_staff.data.api.promotion.request.PromotionRequest
import com.sudo248.soc_staff.data.dto.promotion.PromotionDto
import com.sudo248.soc_staff.domain.common.Constants
import retrofit2.Response
import retrofit2.http.*

@ApiService(baseUrl = BuildConfig.BASE_URL)
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface PromotionService {

    @GET("promotion/")
    suspend fun getAllPromotion(): Response<BaseResponse<List<PromotionDto>>>

    @POST("promotion/")
    suspend fun postPromotion(@Body request: PromotionRequest): Response<BaseResponse<PromotionDto>>

    @PUT("promotion/{promotionId}")
    suspend fun putPromotion(@Path("promotionId") promotionId: String,@Body promotion: PromotionDto): Response<BaseResponse<PromotionDto>>

    @DELETE("promotion/{promotionId}")
    suspend fun deletePromotion(@Path("promotionId") promotionId: String): Response<BaseResponse<Boolean>>
}