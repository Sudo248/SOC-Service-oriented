package com.sudo248.soc_staff.data.di

import com.sudo248.soc.data.repository.AuthRepositoryImpl
import com.sudo248.soc.data.repository.DiscoveryRepositoryImpl
import com.sudo248.soc.data.repository.PaymentRepositoryImpl
import com.sudo248.soc.data.repository.UserRepositoryImpl
import com.sudo248.soc.domain.repository.AuthRepository
import com.sudo248.soc.domain.repository.DiscoveryRepository
import com.sudo248.soc.domain.repository.PaymentRepository
import com.sudo248.soc.domain.repository.UserRepository
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
    abstract fun bindPaymentRepository(paymentRepository: PaymentRepositoryImpl): PaymentRepository
}