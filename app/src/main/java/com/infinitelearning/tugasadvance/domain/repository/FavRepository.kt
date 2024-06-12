package com.infinitelearning.tugasadvance.domain.repository

import com.infinitelearning.tugasadvance.domain.model.FavData
import kotlinx.coroutines.flow.Flow

typealias AllFavList = List<FavData>

interface FavRepository {
    fun getAllFav(): Flow<AllFavList>

    suspend fun getFavById(id: Int): FavData

    suspend fun addFav(favData: FavData)

    suspend fun deleteFav(favData: FavData)
}