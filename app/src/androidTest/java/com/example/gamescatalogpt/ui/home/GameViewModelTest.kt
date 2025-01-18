package com.example.gamescatalogpt.ui.home

import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.usecases.GetGamesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getGamesUseCase: GetGamesUseCase
    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getGamesUseCase = mockk()
        viewModel = GameViewModel(getGamesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `init should call loadGames and populate state with games`() = runTest {
//        val expectedGames = listOf(
//            Game(id = 1, title = "Game 1"),
//            Game(id = 2, title = "Game 2")
//        )
//        coEvery { getGamesUseCase.invoke() } returns expectedGames
//
//        viewModel = GameViewModel(getGamesUseCase)
//
//        viewModel.games.test {
//            assertEquals(emptyList<Game>(), awaitItem())
//            advanceUntilIdle()
//            assertEquals(expectedGames, awaitItem())
//        }
//        coVerify { getGamesUseCase.invoke() }
//    }
//
//    @Test
//    fun `loadGames should update state with games`() = runTest {
//        val expectedGames = listOf(
//            Game(id = 3, title = "Game 3"),
//            Game(id = 4, title = "Game 4")
//        )
//        coEvery { getGamesUseCase.invoke() } returns expectedGames
//        viewModel.loadGames()
//        viewModel.games.test {
//            assertEquals(emptyList<Game>(), awaitItem())
//            advanceUntilIdle()
//            assertEquals(expectedGames, awaitItem())
//        }
//        coVerify { getGamesUseCase.invoke() }
//    }
//
//    @Test
//    fun `loadGames should handle empty list gracefully`() = runTest {
//        coEvery { getGamesUseCase.invoke() } returns emptyList()
//        viewModel.loadGames()
//        viewModel.games.test {
//            assertEquals(emptyList<Game>(), awaitItem())
//            advanceUntilIdle()
//            assertEquals(emptyList<Game>(), awaitItem())
//        }
//        coVerify { getGamesUseCase.invoke() }
//    }
}
