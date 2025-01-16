package com.example.gamescatalogpt.navigation

sealed class AppRoute(val route: String) {
    object Splash : AppRoute("splash")
    object Onboarding: AppRoute("onboarding")
    object Home : AppRoute("Home")
}