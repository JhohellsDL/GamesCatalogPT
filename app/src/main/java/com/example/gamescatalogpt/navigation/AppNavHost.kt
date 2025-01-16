package com.example.gamescatalogpt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamescatalogpt.ui.home.HomeScreen
import com.example.gamescatalogpt.ui.onboarding.OnboardingScreen
import com.example.gamescatalogpt.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route
    ) {
        composable(AppRoute.Splash.route) {
            SplashScreen(navController)
        }
        composable(AppRoute.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(AppRoute.Home.route) {
            HomeScreen()
        }
    }
}