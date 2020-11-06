package br.com.gifs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifEntity(
    @PrimaryKey val id: String,
    val title: String,
    val url: String
)