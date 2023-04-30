package com.sudo248.soc.data.api.cart

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.domain.common.Constants

@ApiService(baseUrl = BuildConfig.BASE_URL + "cart/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface CartService {

}