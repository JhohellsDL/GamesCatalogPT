package com.example.gamescatalogpt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gameViewModel: GameViewModel,
    private val favoritesViewModel: FavoritesViewModel
) : ViewModel() {

    val games: StateFlow<List<Game>> = gameViewModel.games
    val favorites: StateFlow<Set<Int>> = favoritesViewModel.favoritesGamesIds

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredGames: StateFlow<List<Game>> = combine(games, _searchQuery) { games, query ->
        if (query.isBlank()) {
            games
        } else {
            games.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.shortDescription.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _randomGame = MutableLiveData<Game>()
    val randomGame: LiveData<Game> = _randomGame

    fun toggleFavorite(gameId: Int) {
        val game = games.value.find { it.id == gameId } ?: Game()
        favoritesViewModel.toggleFavorite(game, gameId)
    }

    fun randomGame() {
        viewModelScope.launch {
            val currentGames = games.value
            if (currentGames.isNotEmpty()) {
                _randomGame.value = currentGames.random()
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
