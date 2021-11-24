package com.example.zap.core

import androidx.multidex.MultiDexApplication
import com.example.zap.core.koin.adapterModule
import com.example.zap.core.koin.appModule
import com.example.zap.core.koin.repositoryModule
import com.example.zap.core.koin.serviceModule
import com.example.zap.core.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : MultiDexApplication() {
    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    appModule,
                    serviceModule,
                    adapterModule
                )
            )
        }
    }
}
