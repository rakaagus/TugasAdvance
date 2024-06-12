package com.infinitelearning.tugasadvance.di

import com.infinitelearning.tugasadvance.data.repository.FavRepositoryImpl
import com.infinitelearning.tugasadvance.domain.repository.FavRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideFavRepo(favRepositoryImpl: FavRepositoryImpl) : FavRepository
}