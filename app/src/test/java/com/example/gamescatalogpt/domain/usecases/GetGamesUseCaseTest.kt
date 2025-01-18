package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.data.remote.GameResponse
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.repositories.GameRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGamesUseCaseTest {

    private lateinit var repository: GameRepository
    private lateinit var useCase: GetGamesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetGamesUseCase(repository)
    }

    @Test
    fun `invoke should return a list of transformed games`() = runTest {
        val repositoryGames = listOf(
            GameResponse(id = 1, title = "Game 1"),
            GameResponse(id = 2, title = "Game 2")
        )
        val expectedGames = listOf(
            Game(id = 1, title = "Game 1"),
            Game(id = 2, title = "Game 2")
        )
        coEvery { repository.getGames() } returns repositoryGames
        val result = useCase()
        assertEquals(expectedGames, result)
        coVerify { repository.getGames() }
    }

    @Test
    fun `invoke should return an empty list when no games are available`() = runTest {
        coEvery { repository.getGames() } returns emptyList()

        val result = useCase()
        assertEquals(emptyList<Game>(), result)
        coVerify { repository.getGames() }
    }
}