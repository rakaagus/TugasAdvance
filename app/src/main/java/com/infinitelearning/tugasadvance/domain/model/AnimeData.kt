package com.example.weebspedia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeData(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val genreId: List<Int>,
    val description: String,
    val rating: Double = 0.0,
    val eps: Int,
    val date: String,
    val statusEnd: Boolean = false,
): Parcelable
