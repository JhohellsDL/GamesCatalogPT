package com.example.gamescatalogpt.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamescatalogpt.data.local.room.FavoriteGame
import com.example.gamescatalogpt.domain.mappers.toFavoriteGame
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.usecases.GetFavoritesGamesIdsUseCase
import com.example.gamescatalogpt.domain.usecases.GetFavoritesGamesUseCase
import com.example.gamescatalogpt.domain.usecases.RemoveFavoriteGameUseCase
import com.example.gamescatalogpt.domain.usecases.SaveFavoriteGameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesGamesUseCase: GetFavoritesGamesUseCase,
    private val getFavoritesGamesIdsUseCase: GetFavoritesGamesIdsUseCase,
    private val saveFavoriteGameUseCase: SaveFavoriteGameUseCase,
    private val removeFavoriteGameUseCase: RemoveFavoriteGameUseCase
): ViewModel() {

    private val _favoritesGames = MutableStateFlow<List<FavoriteGame>>(emptyList())
    val favoritesGames: StateFlow<List<FavoriteGame>> = _favoritesGames.asStateFlow()

    private val _favoritesGamesIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoritesGamesIds: StateFlow<Set<Int>> = _favoritesGamesIds.asStateFlow()

    fun toggleFavorite(gameItem: Game, gameId: Int) {
        viewModelScope.launch {
            val updatedFavorites = _favoritesGamesIds.value.toMutableSet()
            val isFavorite = gameId in updatedFavorites
            if (isFavorite) updatedFavorites.remove(gameId) else updatedFavorites.add(gameId)

            _favoritesGamesIds.value = updatedFavorites
            val favoriteGame = gameItem.toFavoriteGame()
            handleFavoriteGame(favoriteGame, isFavorite)
        }
    }

    fun handleFavoriteGame(game: FavoriteGame, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                removeFavoriteGameUseCase.invoke(game)
            } else {
                saveFavoriteGameUseCase.invoke(game)
            }
        }
    }

    init {
        loadFavoriteGamesIds()
    }

    private fun loadFavoriteGamesIds() {
        viewModelScope.launch {
            getFavoritesGamesIdsUseCase.execute().collect {
                _favoritesGamesIds.value = it.toSet()
            }
        }
    }

    fun loadFavoriteGames() {
        viewModelScope.launch {
            getFavoritesGamesUseCase.invoke().collect {
                _favoritesGames.value = it
            }
        }
    }
}