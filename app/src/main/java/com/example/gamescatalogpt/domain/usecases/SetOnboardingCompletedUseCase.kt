package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.domain.repositories.OnboardingRepository

class SetOnboardingCompletedUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(completed: Boolean) =
        onboardingRepository.setOnboardingCompleted(completed)
}