package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.domain.repositories.GameRepository

class SaveFavoriteGameUseCase(
    private val repository: GameRepository
) {
    suspend fun invoke(game: FavoriteGame) = repository.saveFavoriteGame(game)
}