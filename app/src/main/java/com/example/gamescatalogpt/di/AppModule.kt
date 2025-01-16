package com.example.gamescatalogpt.di

import com.example.gamescatalogpt.data.local.DataStoreManager
import com.example.gamescatalogpt.data.repositories_impl.OnboardingRepositoryImpl
import com.example.gamescatalogpt.domain.repositories.OnboardingRepository
import com.example.gamescatalogpt.domain.usecases.GetOnboardingStatusUseCase
import com.example.gamescatalogpt.domain.usecases.SetOnboardingCompletedUseCase
import com.example.gamescatalogpt.navigation.AppNavigator
import com.example.gamescatalogpt.navigation.AppNavigatorImpl
import com.example.gamescatalogpt.ui.onboarding.OnboardingViewModel
import com.example.gamescatalogpt.utils.createDataStore
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val repositoryModule = module {
    single<OnboardingRepository> { OnboardingRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetOnboardingStatusUseCase(get()) }
    factory { SetOnboardingCompletedUseCase(get()) }
}

val dataStoreModule = module {
    single { DataStoreManager(get()) }
    single { createDataStore(get()) }
}

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
}

val navigationModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
}

val appModule =
    listOf(viewModelModule, navigationModule, dataStoreModule, useCaseModule, repositoryModule)
