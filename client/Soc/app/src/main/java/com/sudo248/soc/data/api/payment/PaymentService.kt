package com.sudo248.soc.data.api.payment

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.api.discovery.request.PaymentRequest
import com.sudo248.soc.data.dto.payment.PaymentDto
import com.sudo248.soc.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

@ApiService(baseUrl = BuildConfig.BASE_URL + "payment/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface PaymentService {

    @GET("pay")
    suspend fun pay(@Body request: PaymentRequest): Response<BaseResponse<PaymentDto>>
}