package com.infinitelearning.tugasadvance.data.api.response

import com.google.gson.annotations.SerializedName
import com.infinitelearning.tugasadvance.domain.model.Movie

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)