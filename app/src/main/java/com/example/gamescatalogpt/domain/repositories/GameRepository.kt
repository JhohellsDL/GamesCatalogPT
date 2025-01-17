package com.example.gamescatalogpt.domain.repositories

import com.example.gamescatalogpt.data.datasource.GameLocalDataSource
import com.example.gamescatalogpt.data.datasource.GameRemoteDataSource
import com.example.gamescatalogpt.data.local.room.FavoriteGame

class GameRepository(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
) {
    fun getFavoriteGames() = localDataSource.getFavoriteGames()

    suspend fun saveFavoriteGame(game: FavoriteGame) = localDataSource.saveFavoriteGame(game)

    suspend fun removeFavoriteGame(game: FavoriteGame) = localDataSource.removeFavoriteGame(game)

    suspend fun getGames() = remoteDataSource.getGames()
}