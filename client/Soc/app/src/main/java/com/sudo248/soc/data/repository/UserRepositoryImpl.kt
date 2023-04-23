package com.sudo248.soc.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc.data.api.user.UserService
import com.sudo248.soc.data.ktx.data
import com.sudo248.soc.data.mapper.toAddressSuggestion
import com.sudo248.soc.data.mapper.toUserDto
import com.sudo248.soc.data.mapper.toUser
import com.sudo248.soc.domain.entity.user.AddressSuggestion
import com.sudo248.soc.domain.entity.user.Location
import com.sudo248.soc.domain.entity.user.User
import com.sudo248.soc.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val locationService: FusedLocationProviderClient,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun getUserInfo(): DataState<User, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(userService.getUserInfo())
        response.data().toUser()
    }

    override suspend fun updateUser(user: User): DataState<User, Exception> = stateOn(ioDispatcher){
        val userDto = user.toUserDto(getCurrentLocation())
        val response = handleResponse(userService.updateUser(userDto))
        user
    }

    override suspend fun getAddressSuggestionProvince(): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(userService.getAddressSuggestionProvince())
        response.data().map { it.toAddressSuggestion() }
    }

    override suspend fun getAddressSuggestionDistrict(provinceId: Int): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(userService.getAddressSuggestionDistrict(provinceId))
        response.data().map { it.toAddressSuggestion() }
    }

    override suspend fun getAddressSuggestionWard(districtId: Int): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(userService.getAddressSuggestionWard(districtId))
        response.data().map { it.toAddressSuggestion() }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getCurrentLocation(): Location = suspendCoroutine { continuation ->
        locationService.lastLocation.addOnCompleteListener {
            it.result.run {
                continuation.resume(Location(longitude, latitude))
            }
        }
    }
}