package com.example.gamescatalogpt.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.navigation.AppNavigator
import com.example.gamescatalogpt.ui.components.GameItem
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel(),
    navigator: AppNavigator = koinInject()
){
    val games: List<Game> by homeViewModel.games.collectAsState()
    val favorites by homeViewModel.favorites.collectAsState()

    LazyColumn {
        items(games.size) { index ->
            val game = games[index]
            GameItem(
                game = game,
                isFavorite = game.id in favorites,
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