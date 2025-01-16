package com.example.gamescatalogpt.domain.usecases

import com.example.gamescatalogpt.domain.repositories.OnboardingRepository

class GetOnboardingStatusUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke() = onboardingRepository.isOnboardingCompleted()
}