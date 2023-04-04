package com.sudo248.soc.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc.data.api.user.UserService
import com.sudo248.soc.data.ktx.data
import com.sudo248.soc.data.mapper.toUser
import com.sudo248.soc.domain.entity.user.User
import com.sudo248.soc.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun getUserInfo(): DataState<User, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(userService.getUserInfo())
        response.data().toUser()
    }
}