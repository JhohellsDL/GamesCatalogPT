package com.example.gamescatalogpt.data.repositories_impl

import com.example.gamescatalogpt.data.local.DataStoreManager
import com.example.gamescatalogpt.domain.repositories.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class OnboardingRepositoryImpl(
    private val dataStoreManager: DataStoreManager
): OnboardingRepository {
    override fun isOnboardingCompleted(): Flow<Boolean> {
        return dataStoreManager.onboardingCompletedFlow
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStoreManager.markOnboardingCompleted(completed)
    }
}