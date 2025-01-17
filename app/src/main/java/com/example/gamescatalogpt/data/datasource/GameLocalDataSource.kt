package com.example.gamescatalogpt.data.datasource

import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.data.local.room.FavoriteGameDao
import kotlinx.coroutines.flow.Flow

class GameLocalDataSource(private val dao: FavoriteGameDao ) {

    fun getFavoriteGames(): Flow<List<FavoriteGame>> = dao.getFavoriteGames()

    suspend fun saveFavoriteGame(game: FavoriteGame) = dao.saveFavoriteGame(game)

    suspend fun removeFavoriteGame(game: FavoriteGame) = dao.removeFavoriteGame(game)

}