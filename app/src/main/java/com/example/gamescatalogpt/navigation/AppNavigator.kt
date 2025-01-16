package com.example.gamescatalogpt.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun navigateToHome(navController: NavController, route: AppRoute)
    fun navigateToOnboarding(navController: NavController)
}