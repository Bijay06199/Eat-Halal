package com.example.eatthalal.utils

import android.app.Application
import com.example.eatthalal.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class ZorbiStore:Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZorbiStore)
            androidFileProperties()
            modules(
                listOf(
                   viewModelModule, apiModule, appModule, repositoryModule, persistenceDataModule

                )
            )
        }
    }
}