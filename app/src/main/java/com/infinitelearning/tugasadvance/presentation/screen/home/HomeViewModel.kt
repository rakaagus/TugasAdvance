package com.infinitelearning.tugasadvance.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitelearning.tugasadvance.data.api.ApiService
import com.infinitelearning.tugasadvance.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
) : ViewModel() {
    private val apiKey = "f6c7c9267315593505339c445012f5e5"

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchPopularMovies(apiKey)
    }

    private fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                Log.e("MovieViewModel", "Movies fetched successfully")
                val response = apiService.getPopularMovies(apiKey, 1)
                _movies.value = response.results

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Failed to fetch movies", e)
                e.printStackTrace()
            }
        }
    }
}
