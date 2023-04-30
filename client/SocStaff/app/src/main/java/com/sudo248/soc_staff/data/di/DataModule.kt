package com.sudo248.soc_staff.data.di

import com.google.gson.GsonBuilder
import com.sudo248.base_android.data.api.ApiService
import com.sudo248.base_android.data.api.api
import com.sudo248.soc_staff.data.api.auth.AuthService
import com.sudo248.soc_staff.data.api.discovery.DiscoveryService
import com.sudo248.soc_staff.data.api.image.ImageService
import com.sudo248.soc_staff.data.api.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideAuthService(): AuthService = ApiService()

    @Singleton
    @Provides
    fun provideDiscoveryService(): DiscoveryService = ApiService()

    @Singleton
    @Provides
    fun provideUserService(): UserService = api {
        converterFactory = GsonConverterFactory.create(
            GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create()
        )
    }

    @Singleton
    @Provides
    fun provideImageService(): ImageService = ApiService()

    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO

}