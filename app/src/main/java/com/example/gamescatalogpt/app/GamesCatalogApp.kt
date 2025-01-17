package com.example.gamescatalogpt.app

import android.app.Application
import com.example.gamescatalogpt.data.local.room.AppDatabase
import com.example.gamescatalogpt.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GamesCatalogApp: Application() {
    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.getInstance(this)
        startKoin {
            androidContext(this@GamesCatalogApp)
            modules(appModule)
        }
    }
}