package com.example.gamescatalogpt.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamescatalogpt.ui.components.GamesNavigationBar
import com.example.gamescatalogpt.ui.detail.DetailScreen
import com.example.gamescatalogpt.ui.favorites.FavoritesScreen
import com.example.gamescatalogpt.ui.home.HomeScreen
import com.example.gamescatalogpt.ui.onboarding.OnboardingScreen
import com.example.gamescatalogpt.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            if (isBottomNavVisible(navController = navController)) {
                GamesNavigationBar(navController = navController)
            }
        },
        modifier = Modifier.fillMaxSize()
            .background(colorScheme.background)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.Splash.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            composable(AppRoute.Splash.route) {
                SplashScreen(navController)
            }
            composable(AppRoute.Onboarding.route) {
                OnboardingScreen(navController)
            }
            composable(AppRoute.Home.route) {
                HomeScreen(navController)
            }
            composable(AppRoute.Favorites.route) {
                FavoritesScreen(navController)
            }
            composable(
                route = AppRoute.Detail.route,
                arguments = listOf(
                    navArgument("gameId") { type = NavType.IntType },
                    navArgument("isFavorite") { type = NavType.BoolType }
                )
            ) {
                val gameId = it.arguments?.getInt("gameId") ?: 0
                val isFavorite = it.arguments?.getBoolean("isFavorite") == true
                DetailScreen(
                    navController = navController,
                    gameId = gameId,
                    isFavorite = isFavorite
                )
            }
        }
    }
}

@Composable
fun isBottomNavVisible(navController: NavHostController): Boolean {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    return when (currentBackStackEntry?.destination?.route) {
        AppRoute.Home.route,
        AppRoute.Favorites.route -> true

        else -> false
    }
}