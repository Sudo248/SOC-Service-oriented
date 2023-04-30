package com.sudo248.soc_staff.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc_staff.data.api.user.UserService
import com.sudo248.soc_staff.data.ktx.data
import com.sudo248.soc_staff.data.ktx.errorBody
import com.sudo248.soc_staff.data.mapper.toAddressSuggestion
import com.sudo248.soc_staff.data.mapper.toUser
import com.sudo248.soc_staff.data.mapper.toUserDto
import com.sudo248.soc_staff.domain.entity.user.AddressSuggestion
import com.sudo248.soc_staff.domain.entity.user.Location
import com.sudo248.soc_staff.domain.entity.user.User
import com.sudo248.soc_staff.domain.repository.UserRepository
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

    override suspend fun updateUser(user: User, isUpdate: Boolean): DataState<User, Exception> = stateOn(ioDispatcher){
        val location = if (isUpdate) getCurrentLocation() else null
        val userDto = user.toUserDto(location)
        val response = handleResponse(userService.updateUser(userDto))
        if (response.isSuccess) {
            response.data().toUser()
        } else {
            throw response.error().errorBody()
        }
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
//        locationService.lastLocation.addOnCompleteListener {
//            it.result.run {
//                continuation.resume(Location(longitude, latitude))
//            }
//        }

        locationService.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).addOnCompleteListener {
            it.result.run {
                continuation.resume(Location(longitude, latitude))
            }
        }
    }
}