package com.example.gamescatalogpt.di

import androidx.room.Room
import com.example.gamescatalogpt.data.datasource.GameLocalDataSource
import com.example.gamescatalogpt.data.datasource.GameRemoteDataSource
import com.example.gamescatalogpt.data.local.DataStoreManager
import com.example.gamescatalogpt.data.local.room.AppDatabase
import com.example.gamescatalogpt.data.remote.GamesApi
import com.example.gamescatalogpt.data.repositories_impl.OnboardingRepositoryImpl
import com.example.gamescatalogpt.domain.repositories.GameRepository
import com.example.gamescatalogpt.domain.repositories.OnboardingRepository
import com.example.gamescatalogpt.domain.usecases.GetFavoritesGamesIdsUseCase
import com.example.gamescatalogpt.domain.usecases.GetFavoritesGamesUseCase
import com.example.gamescatalogpt.domain.usecases.GetGamesUseCase
import com.example.gamescatalogpt.domain.usecases.GetOnboardingStatusUseCase
import com.example.gamescatalogpt.domain.usecases.RemoveFavoriteGameUseCase
import com.example.gamescatalogpt.domain.usecases.SaveFavoriteGameUseCase
import com.example.gamescatalogpt.domain.usecases.SetOnboardingCompletedUseCase
import com.example.gamescatalogpt.navigation.AppNavigator
import com.example.gamescatalogpt.navigation.AppNavigatorImpl
import com.example.gamescatalogpt.ui.detail.DetailViewModel
import com.example.gamescatalogpt.ui.home.GameViewModel
import com.example.gamescatalogpt.ui.home.HomeViewModel
import com.example.gamescatalogpt.ui.onboarding.OnboardingViewModel
import com.example.gamescatalogpt.utils.BASE_URL
import com.example.gamescatalogpt.utils.createDataStore
import com.example.gamescatalogpt.ui.favorites.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataSourceModule = module {
    single<GameLocalDataSource> { GameLocalDataSource(get()) }
    single<GameRemoteDataSource> { GameRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<OnboardingRepository> { OnboardingRepositoryImpl(get()) }
    single { GameRepository(get(), get()) }
}

val useCaseModule = module {
    factory { GetOnboardingStatusUseCase(get()) }
    factory { SetOnboardingCompletedUseCase(get()) }
    factory { GetGamesUseCase(get()) }
    factory { GetFavoritesGamesUseCase(get()) }
    factory { GetFavoritesGamesIdsUseCase(get()) }
    factory { RemoveFavoriteGameUseCase(get()) }
    factory { SaveFavoriteGameUseCase(get()) }
}

val dataStoreModule = module {
    single { DataStoreManager(get()) }
    single { createDataStore(get()) }
}

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::GameViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

val navigationModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(GamesApi::class.java)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().favoriteGameDao() }
}

val appModule =
    listOf(
        viewModelModule,
        navigationModule,
        dataStoreModule,
        useCaseModule,
        repositoryModule,
        networkModule,
        databaseModule,
        dataSourceModule
    )
