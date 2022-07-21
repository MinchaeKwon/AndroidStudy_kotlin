package com.example.androidstudy_kotlin

import android.app.Application
import android.webkit.WebView
import com.example.androidstudy_kotlin.module.networkModule
import com.example.androidstudy_kotlin.module.repositoryModule
import com.example.androidstudy_kotlin.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PracticeApplication)
            modules(
                listOf(
                    networkModule, repositoryModule, viewModelModule
                )
            )
        }
    }
}