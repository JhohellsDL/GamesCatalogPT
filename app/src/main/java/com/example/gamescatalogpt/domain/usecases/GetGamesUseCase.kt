package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.domain.mappers.toGame
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.repositories.GameRepository

class GetGamesUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(): List<Game> = repository.getGames().map {
        it.toGame()
    }
}