package com.infinitelearning.tugasadvance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infinitelearning.tugasadvance.domain.model.FavData
import com.infinitelearning.tugasadvance.domain.repository.AllFavList
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao{
    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun getAllFav(): Flow<AllFavList>

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    suspend fun getFavById(id: Int): FavData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(favData: FavData)

    @Delete
    suspend fun deleteFav(favData: FavData)
}