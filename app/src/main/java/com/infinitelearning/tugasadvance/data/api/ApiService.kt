package com.infinitelearning.tugasadvance.data.api

import com.infinitelearning.tugasadvance.data.api.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieResponse
}