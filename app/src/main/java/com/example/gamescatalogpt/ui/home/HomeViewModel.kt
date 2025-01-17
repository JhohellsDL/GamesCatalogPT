package com.example.gamescatalogpt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.favorites.FavoritesViewModel

class HomeViewModel(
    private val gameViewModel: GameViewModel,
    private val favoritesViewModel: FavoritesViewModel
) : ViewModel() {

    var games = gameViewModel.games
    var favorites = favoritesViewModel.favoritesGamesIds

    private var _randomGame: MutableLiveData<Game> = MutableLiveData()
    val randomGame: LiveData<Game> = _randomGame

    fun toggleFavorite(gameId: Int) {
        val game = games.value.find { it.id == gameId } ?: Game()
        favoritesViewModel.toggleFavorite(game, gameId)
    }

    fun randomGame(games: List<Game>) {
        _randomGame.value = games.random()
    }

}