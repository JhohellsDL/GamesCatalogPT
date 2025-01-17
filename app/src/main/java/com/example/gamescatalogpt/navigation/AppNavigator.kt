package com.example.gamescatalogpt.navigation

import androidx.navigation.NavController
import com.example.gamescatalogpt.data.local.room.FavoriteGame

interface AppNavigator {
    fun navigateToHome(navController: NavController, route: AppRoute)
    fun navigateToOnboarding(navController: NavController)
    fun navigateToDetail(navController: NavController, gameId: Int, isFavoriteGame: Boolean = false)
}