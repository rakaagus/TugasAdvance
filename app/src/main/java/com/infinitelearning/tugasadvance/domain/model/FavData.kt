package com.infinitelearning.tugasadvance.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val image: String?,
)