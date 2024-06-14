package com.infinitelearning.tugasadvance.di

import com.infinitelearning.tugasadvance.data.repository.AuthRepositoryImpl
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

}