package com.example.gamescatalogpt.domain.mappers

import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.data.remote.GameResponse
import com.example.gamescatalogpt.domain.models.Game

fun FavoriteGame.toGame(): Game {
    return Game(
        id = gameId,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        freeToGameProfileUrl = freeToGameProfileUrl,
        isFavorite = true
    )
}

fun GameResponse.toGame() = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    gameUrl = gameUrl,
    genre = genre,
    platform = platform,
    publisher = publisher,
    developer = developer,
    releaseDate = releaseDate,
    freeToGameProfileUrl = freeToGameProfileUrl
)

fun Game.toFavoriteGame(): FavoriteGame {
    return FavoriteGame(
        gameId = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        freeToGameProfileUrl = freeToGameProfileUrl
    )
}