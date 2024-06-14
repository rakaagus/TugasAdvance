package com.infinitelearning.tugasadvance.di

import android.content.Context
import androidx.room.Room
import com.infinitelearning.tugasadvance.data.network.FavDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFavDatabase(@ApplicationContext context: Context): FavDb {
        return Room.databaseBuilder(
            context,
            FavDb::class.java,
            "favorite_table"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavDao(db: FavDb) = db.favDao
}