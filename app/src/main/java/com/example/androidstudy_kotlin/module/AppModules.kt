package com.example.androidstudy_kotlin.module

import com.example.androidstudy_kotlin.data.AppRepository
import com.example.androidstudy_kotlin.BuildConfig
import com.example.androidstudy_kotlin.data.RequestInterceptor
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * AppModule 설정
 */
var networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr")
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    single {
        OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor(get()))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
    }
}

var repositoryModule = module {
    single { AppRepository(get()) }
}

var viewModelModule = module {
    single { BaseViewModel() }
    factory { MainViewModel(get()) }
}