package com.example.gamescatalogpt.data.remote

import retrofit2.http.GET

interface GamesApi {
    @GET("games")
    suspend fun getGames(): List<GameResponse>
}
