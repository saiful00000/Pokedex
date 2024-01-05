package com.shaiful.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PokedexApplication : Application() {

    // To use timber library we must initialize it here
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}