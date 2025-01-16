package com.example.gamescatalogpt.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamescatalogpt.domain.usecases.GetOnboardingStatusUseCase
import com.example.gamescatalogpt.domain.usecases.SetOnboardingCompletedUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getOnboardingStatusUseCase: GetOnboardingStatusUseCase,
    private val setOnboardingCompletedUseCase: SetOnboardingCompletedUseCase
) : ViewModel() {

    val onboardingCompleted = getOnboardingStatusUseCase.invoke()

    fun setOnboardingCompleted() {
        viewModelScope.launch {
            setOnboardingCompletedUseCase(true)
        }
    }
}