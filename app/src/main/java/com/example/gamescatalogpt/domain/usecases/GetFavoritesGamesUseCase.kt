package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.domain.repositories.GameRepository

class GetFavoritesGamesUseCase(
    private val repository: GameRepository
) {
    operator fun invoke() = repository.getFavoriteGames()
}