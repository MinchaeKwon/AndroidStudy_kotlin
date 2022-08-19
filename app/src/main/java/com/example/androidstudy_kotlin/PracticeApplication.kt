package com.example.androidstudy_kotlin

import android.app.Application
import android.provider.Settings
import android.webkit.WebView
import com.example.androidstudy_kotlin.module.networkModule
import com.example.androidstudy_kotlin.module.repositoryModule
import com.example.androidstudy_kotlin.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PracticeApplication : Application() {
    init {
        context = this
    }

    override fun onCreate() {
        super.onCreate()

        appApplication = this

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

    companion object {
        var context: PracticeApplication? = null
            private set

        lateinit var appApplication: PracticeApplication

        fun getAndroidID(): String {
            return Settings.Secure.getString(appApplication.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }
}