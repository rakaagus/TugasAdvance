package com.infinitelearning.tugasadvance.presentation.screen.fav

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitelearning.tugasadvance.domain.model.FavData
import com.infinitelearning.tugasadvance.domain.repository.FavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val favRepo: FavRepository
): ViewModel() {

    var fav by mutableStateOf(FavData(0, "", ""))
    val favList = favRepo.getAllFav()

    fun getFavById(id: Int) = viewModelScope.launch {
        fav = favRepo.getFavById(id)
    }

    fun addFav(favData: FavData) = viewModelScope.launch {
        favRepo.addFav(favData)
    }

    fun deleteFav(favData: FavData) = viewModelScope.launch {
        favRepo.deleteFav(favData)
    }
}