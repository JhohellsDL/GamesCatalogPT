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

class GetFavoritesGamesIdsUseCaseTest {
    private lateinit var repository: GameRepository
    private lateinit var useCase: GetFavoritesGamesIdsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetFavoritesGamesIdsUseCase(repository)
    }

    @Test
    fun `return list of favorite game IDs`() = runTest {
        val favoriteGames = listOf(
            FavoriteGame(gameId = 1),
            FavoriteGame(gameId = 2),
            FavoriteGame(gameId = 3)
        )
        coEvery { repository.getFavoriteGames() } returns flowOf(favoriteGames)

        val result: Flow<List<Int>> = useCase.execute()

        result.collect { ids ->
            assertEquals(listOf(1, 2, 3), ids)
        }
    }

    @Test
    fun `return empty list when no favorite games`() = runTest {
        coEvery { repository.getFavoriteGames() } returns flowOf(emptyList())

        val result: Flow<List<Int>> = useCase.execute()

        result.collect { ids ->
            assertEquals(emptyList<Int>(), ids)
        }
    }
}