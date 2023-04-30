package com.sudo248.soc.data.api.user

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.dto.user.AddressSuggestionDto
import com.sudo248.soc.data.dto.user.UserDto
import com.sudo248.soc.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

@ApiService(baseUrl = BuildConfig.BASE_URL)
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface UserService {
    @GET("user/")
    suspend fun getUserInfo(): Response<BaseResponse<UserDto>>

    @PUT("user/")
    suspend fun updateUser(@Body userDto: UserDto): Response<BaseResponse<UserDto>>

    @GET("user/address/suggestion/province")
    suspend fun getAddressSuggestionProvince(): Response<BaseResponse<List<AddressSuggestionDto>>>

    @GET("user/address/suggestion/district/{provinceId}")
    suspend fun getAddressSuggestionDistrict(
        @Path("provinceId") provinceId: Int
    ): Response<BaseResponse<List<AddressSuggestionDto>>>

    @GET("user/address/suggestion/ward/{districtId}")
    suspend fun getAddressSuggestionWard(
        @Path("districtId") districtId: Int
    ): Response<BaseResponse<List<AddressSuggestionDto>>>
}