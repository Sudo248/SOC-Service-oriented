package com.sudo248.soc.data.api.auth

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.api.auth.request.AccountRequest
import com.sudo248.soc.data.api.auth.request.ChangePasswordRequest
import com.sudo248.soc.data.api.auth.request.OtpRequest
import com.sudo248.soc.data.dto.TokenDto
import com.sudo248.soc.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 10:21 - 23/02/2023
 */

@ApiService(baseUrl = BuildConfig.BASE_URL + "auth/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface AuthService {

    @POST("sign-in")
    suspend fun signIn(@Body accountRequest: AccountRequest): Response<BaseResponse<TokenDto>>

    @POST("sign-up")
    suspend fun signUp(@Body accountRequest: AccountRequest): Response<BaseResponse<Unit>>

    @POST("change-password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Response<BaseResponse<Unit>>

    @POST("generate-otp/{phoneNumber}")
    suspend fun generateOtp(@Path("phoneNumber") phoneNumber: String): Response<BaseResponse<Unit>>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body otp: OtpRequest): Response<BaseResponse<TokenDto>>

    @GET("logout")
    suspend fun logout(): Response<BaseResponse<Unit>>
}