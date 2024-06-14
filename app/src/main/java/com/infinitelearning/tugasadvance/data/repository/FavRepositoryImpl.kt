package com.infinitelearning.tugasadvance.data.repository

import com.infinitelearning.tugasadvance.data.dao.FavDao
import com.infinitelearning.tugasadvance.domain.model.FavData
import com.infinitelearning.tugasadvance.domain.repository.AllFavList
import com.infinitelearning.tugasadvance.domain.repository.FavRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavRepositoryImpl @Inject constructor(
    private val favDao: FavDao
) : FavRepository {
    override fun getAllFav(): Flow<AllFavList> {
        return favDao.getAllFav()
    }

    override suspend fun getFavById(id: Int): FavData {
        return favDao.getFavById(id)
    }

    override suspend fun addFav(favData: FavData) {
        return favDao.addFav(favData)
    }

    override suspend fun deleteFav(favData: FavData) {
        return favDao.deleteFav(favData)
    }

}