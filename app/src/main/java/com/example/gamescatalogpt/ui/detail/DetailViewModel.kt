package com.example.gamescatalogpt.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state

    fun loadGameById(id: Int) {
        viewModelScope.launch {
            _state.value = try {
                val game = getGamesUseCase.invoke().find { it.id == id }
                if (game != null) {
                    DetailState.Success(game)
                } else {
                    DetailState.Error("Juego no encontrado")
                }
            } catch (_: Exception) {
                DetailState.Error("Error al cargar el juego")
            }
        }
    }
}

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val game: Game) : DetailState()
    data class Error(val message: String) : DetailState()
}