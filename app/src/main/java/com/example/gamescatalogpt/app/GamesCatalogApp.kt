package com.example.gamescatalogpt.app

import android.app.Application
import com.example.gamescatalogpt.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GamesCatalogApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GamesCatalogApp)
            modules(appModule)
        }
    }
}