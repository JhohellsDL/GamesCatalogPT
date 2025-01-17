package com.example.gamescatalogpt.navigation

sealed class AppRoute(val route: String) {
    object Splash : AppRoute("splash")
    object Onboarding: AppRoute("onboarding")
    object Home : AppRoute("Home")
    object Favorites : AppRoute("favorites")
    object Detail : AppRoute("detail/{gameId}/{isFavorite}") {
        fun createRoute(gameId: Int, isFavoriteGame: Boolean) = "detail/$gameId/$isFavoriteGame"
    }
}