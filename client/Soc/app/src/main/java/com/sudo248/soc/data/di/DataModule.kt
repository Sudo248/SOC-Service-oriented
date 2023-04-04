package com.sudo248.soc.data.di

import android.content.Context
import com.sudo248.base_android.data.api.ApiService
import com.sudo248.soc.data.api.auth.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.sudo248.soc.BuildConfig
import com.sudo248.soc.data.api.discovery.DiscoveryService
import com.sudo248.soc.data.api.user.UserService


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
    fun provideUserService(): UserService = ApiService()

    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO

}