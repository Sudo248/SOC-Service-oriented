package com.sudo248.soc_staff.data.di

import com.sudo248.soc_staff.data.repository.*
import com.sudo248.soc_staff.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:46 - 05/03/2023
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindDiscoveryRepository(discoveryRepository: DiscoveryRepositoryImpl): DiscoveryRepository

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindImageRepository(imageRepository: ImageRepositoryImpl): ImageRepository

    @Binds
    abstract fun bindPromotionRepository(promotionRepository: PromotionRepositoryImpl): PromotionRepository

}