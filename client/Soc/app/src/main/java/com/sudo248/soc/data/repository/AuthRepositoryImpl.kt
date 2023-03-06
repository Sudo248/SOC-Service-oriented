package com.sudo248.soc.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.exception.ApiException
import com.sudo248.base_android.ktx.state
import com.sudo248.soc.data.api.auth.AuthService
import com.sudo248.soc.data.api.auth.request.AccountRequest
import com.sudo248.soc.data.api.auth.request.ChangePasswordRequest
import com.sudo248.soc.data.api.auth.request.OtpRequest
import com.sudo248.soc.data.ktx.fromResponse
import com.sudo248.soc.data.mapper.toToken
import com.sudo248.soc.domain.entity.auth.Account
import com.sudo248.soc.domain.entity.auth.Token
import com.sudo248.soc.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:32 - 05/03/2023
 */

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun signIn(account: Account): DataState<Token, Exception> = state {
        val request = AccountRequest(
            phoneNumber = account.phoneNumber,
            password = account.password,
            provider = account.provider
        )
        val response = authService.signIn(request)
        if (response.success) {
            response.data!!.toToken()
        } else {
            throw ApiException.fromResponse(response)
        }
    }

    override suspend fun signUp(account: Account): DataState<Unit, Exception> = state {
        val request = AccountRequest(
            phoneNumber = account.phoneNumber,
            password = account.password,
            provider = account.provider
        )
        val response = authService.signUp(request)
        if (!response.success) {
            throw ApiException.fromResponse(response)
        }
    }

    override suspend fun generateOtp(phoneNumber: String): DataState<Unit, Exception> = state {
        val response = authService.generateOtp(phoneNumber)
        if (!response.success) {
            throw ApiException.fromResponse(response)
        }
    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): DataState<Token, Exception> = state {
        val request = OtpRequest(
            phoneNumber,
            otp
        )
        val response = authService.verifyOtp(request)
        if (response.success) {
            response.data!!.toToken()
        } else {
            throw ApiException.fromResponse(response)
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassWord: String
    ): DataState<Unit, Exception> = state {
        val request = ChangePasswordRequest(
            oldPassword,
            newPassWord
        )
        val response = authService.changePassword(request)
        if (!response.success) {
            throw ApiException.fromResponse(response)
        }
    }

    override suspend fun logout(): DataState<Unit, Exception> = state {
        authService.logout()
    }
}