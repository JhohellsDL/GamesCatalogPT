package com.example.gamescatalogpt.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.domain.mappers.toGame
import com.example.gamescatalogpt.navigation.AppNavigator
import com.example.gamescatalogpt.ui.components.GameItem
import com.example.gamescatalogpt.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    navigator: AppNavigator = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavoriteGames()
    }

    val gamesFavorites: List<FavoriteGame> by viewModel.favoritesGames.collectAsState()

    LazyColumn {
        items(gamesFavorites.size) { index ->
            val game = gamesFavorites[index].toGame()
            GameItem(
                game = game,
                isFavorite = true,
                onFavoriteToggle = {
                    homeViewModel.toggleFavorite(game.id)
                },
                onClickItem = {
                    navigator.navigateToDetail(navController, game.id, true)
                }
            )

        }
    }
}