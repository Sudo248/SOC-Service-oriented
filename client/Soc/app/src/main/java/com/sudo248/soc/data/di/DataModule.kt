package com.sudo248.soc.data.di

import com.sudo248.base_android.data.api.ApiService
import com.sudo248.soc.data.api.auth.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 10:18 - 23/02/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService {
        return ApiService()
    }

}