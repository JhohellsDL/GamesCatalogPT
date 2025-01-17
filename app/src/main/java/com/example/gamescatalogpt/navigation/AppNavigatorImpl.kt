package com.example.gamescatalogpt.navigation

import androidx.navigation.NavController


class AppNavigatorImpl : AppNavigator {
    override fun navigateToHome(navController: NavController, route: AppRoute) {
        navController.navigate(AppRoute.Home.route) {
            popUpTo(route.route) { inclusive = true }
        }
    }

    override fun navigateToOnboarding(navController: NavController) {
        navController.navigate(AppRoute.Onboarding.route) {
            popUpTo(AppRoute.Splash.route) { inclusive = true }
        }
    }

    override fun navigateToDetail(
        navController: NavController,
        gameId: Int,
        isFavorite: Boolean
    ) {
        navController.navigate(AppRoute.Detail.createRoute(gameId, isFavorite))
    }
}
