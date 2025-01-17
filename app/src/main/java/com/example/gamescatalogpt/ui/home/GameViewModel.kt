package com.example.gamescatalogpt.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private var _games: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games: StateFlow<List<Game>> = _games.asStateFlow()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            val recipes = getGamesUseCase.invoke()
            _games.value = recipes
        }
    }
}