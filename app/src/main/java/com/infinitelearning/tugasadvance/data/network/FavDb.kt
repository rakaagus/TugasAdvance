package com.infinitelearning.tugasadvance.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infinitelearning.tugasadvance.data.dao.FavDao
import com.infinitelearning.tugasadvance.domain.model.FavData

@Database(
    entities = [FavData::class],
    version = 1,
    exportSchema = false,
)
abstract class FavDb : RoomDatabase(){
    abstract val favDao: FavDao
}