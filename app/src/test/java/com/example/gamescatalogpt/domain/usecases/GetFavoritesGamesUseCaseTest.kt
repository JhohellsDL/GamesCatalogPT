package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.domain.repositories.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetFavoritesGamesUseCaseTest {
    private lateinit var repository: GameRepository
    private lateinit var useCase: GetFavoritesGamesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetFavoritesGamesUseCase(repository)
    }

    @Test
    fun `invoke should return flow of favorite games`() = runTest {
        val favoriteGames = listOf(
            FavoriteGame(gameId = 1),
            FavoriteGame(gameId = 2)
        )
        coEvery { repository.getFavoriteGames() } returns flowOf(favoriteGames)

        val result: Flow<List<FavoriteGame>> = useCase()

        result.collect { games ->
            assertEquals(favoriteGames, games)
        }
    }

    @Test
    fun `invoke should return empty flow when no favorite games`() = runTest {
        coEvery { repository.getFavoriteGames() } returns flowOf(emptyList())

        val result: Flow<List<FavoriteGame>> = useCase()

        result.collect { games ->
            assertEquals(emptyList<FavoriteGame>(), games)
        }
    }
}