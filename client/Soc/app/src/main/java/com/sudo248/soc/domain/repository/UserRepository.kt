package com.sudo248.soc.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc.domain.entity.user.User

interface UserRepository {
    suspend fun getUserInfo(): DataState<User, Exception>
}