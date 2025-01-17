package com.example.gamescatalogpt.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gamescatalogpt.utils.EMPTY

@Entity(tableName = "favorite_games")
data class FavoriteGame(
    @PrimaryKey
    val gameId: Int,
    val title: String = EMPTY,
    val thumbnail: String = EMPTY,
    val shortDescription: String = EMPTY,
    val gameUrl: String = EMPTY,
    val genre: String = EMPTY,
    val platform: String = EMPTY,
    val publisher: String = EMPTY,
    val developer: String = EMPTY,
    val releaseDate: String = EMPTY,
    val freeToGameProfileUrl: String = EMPTY
)