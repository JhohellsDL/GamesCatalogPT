package com.example.gamescatalogpt.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.gamescatalogpt.navigation.AppNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel(),
    navigator: AppNavigator = koinInject()
){
    val games by homeViewModel.filteredGames.collectAsState()
    val favorites by homeViewModel.favorites.collectAsState()
    val randomGame by homeViewModel.randomGame.observeAsState()
    val searchQuery by homeViewModel.searchQuery.collectAsState()

    LaunchedEffect(Unit) {
        if (games.isNotEmpty()) {
            homeViewModel.randomGame(games)
        }
    }

    HomeScreenContent(
        games = games,
        favorites = favorites,
        onFavoriteClick = homeViewModel::toggleFavorite,
        navigatorClick = { navigator.navigateToDetail(navController, it, false) },
        onRandomClick = { homeViewModel.randomGame(games) },
        randomGame = randomGame,
        searchQuery = searchQuery,
        onSearchQueryGame = homeViewModel::updateSearchQuery,
    )
}