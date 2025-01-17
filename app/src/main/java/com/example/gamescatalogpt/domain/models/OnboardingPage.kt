package com.example.gamescatalogpt.domain.models


data class OnboardingPage(val title: String, val description: String)

val onboardingPages = listOf(
    OnboardingPage(
        title = "¡Bienvenido a Catalog Game!",
        description = "Explora los juegos más emocionantes y populares."
    ),
    OnboardingPage(
        title = "Descubre Nuevos Juegos",
        description = "Filtra y busca juegos según tus preferencias."
    ),
    OnboardingPage(
        title = "Crea tu Colección",
        description = "Guarda tus juegos favoritos en tu lista personal."
    )
)
