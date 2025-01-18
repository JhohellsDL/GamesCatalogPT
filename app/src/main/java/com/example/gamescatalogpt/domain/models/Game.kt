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
) {
    companion object{
        val gameMock = Game(
            id = 582,
            title = "Tarisland",
            thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
            shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
            gameUrl = "https://www.freetogame.com/open/tarisland",
            genre = "MMORPG",
            platform = "PC (Windows)",
            publisher = "Tencent",
            developer = "Level Infinite",
            releaseDate = "2024-06-22",
            freeToGameProfileUrl = "https://www.freetogame.com/tarisland"
        )
    }
}