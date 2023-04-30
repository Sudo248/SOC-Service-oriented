package com.sudo248.soc_staff.domain.repository

import android.provider.ContactsContract.Data
import com.sudo248.base_android.core.DataState
import com.sudo248.soc.domain.entity.user.AddressSuggestion
import com.sudo248.soc.domain.entity.user.User

interface UserRepository {
    suspend fun getUserInfo(): DataState<User, Exception>
    suspend fun updateUser(user: User): DataState<User, Exception>
    suspend fun getAddressSuggestionProvince(): DataState<List<AddressSuggestion>, Exception>
    suspend fun getAddressSuggestionDistrict(provinceId: Int): DataState<List<AddressSuggestion>, Exception>
    suspend fun getAddressSuggestionWard(districtId: Int): DataState<List<AddressSuggestion>, Exception>
}