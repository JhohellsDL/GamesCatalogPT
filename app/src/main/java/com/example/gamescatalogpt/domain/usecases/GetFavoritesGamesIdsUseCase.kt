package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.domain.repositories.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoritesGamesIdsUseCase(
    private val repository: GameRepository
) {
    fun execute(): Flow<List<Int>> {
        return repository.getFavoriteGames().map {
            it.map { it.gameId }
        }
    }
}