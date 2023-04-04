package com.sudo248.soc.data.api.user

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.dto.user.UserDto
import com.sudo248.soc.domain.common.Constants
import retrofit2.Response
import retrofit2.http.GET

@ApiService(baseUrl = BuildConfig.BASE_URL + "user/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface UserService {
    @GET("info")
    suspend fun getUserInfo(): Response<BaseResponse<UserDto>>
}