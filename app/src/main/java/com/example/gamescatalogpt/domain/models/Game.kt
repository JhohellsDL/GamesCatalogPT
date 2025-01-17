package com.example.gamescatalogpt.domain.models

import com.example.gamescatalogpt.utils.EMPTY

data class Game(
    val id: Int = 0,
    val title: String = EMPTY,
    val thumbnail: String = EMPTY,
    val shortDescription: String = EMPTY,
    val gameUrl: String = EMPTY,
    val genre: String = EMPTY,
    val platform: String = EMPTY,
    val publisher: String = EMPTY,
    val developer: String = EMPTY,
    val releaseDate: String = EMPTY,
    val freeToGameProfileUrl: String = EMPTY,
    val isFavorite: Boolean = false
)