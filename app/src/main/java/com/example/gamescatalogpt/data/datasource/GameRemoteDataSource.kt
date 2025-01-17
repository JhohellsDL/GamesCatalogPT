package com.example.gamescatalogpt.data.datasource

import com.example.gamescatalogpt.data.remote.GameResponse
import com.example.gamescatalogpt.data.remote.GamesApi

class GameRemoteDataSource(private val api: GamesApi ) {

    suspend fun getGames(): List<GameResponse> {
        try {
            return api.getGames()
        } catch (e: Exception) {
            throw e
        }
    }
}