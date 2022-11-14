package com.ajdamiao.recipesapp

import android.app.Application
import com.ajdamiao.recipesapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipesAPP: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RecipesAPP)
            modules(mainModule)
        }
    }
}