package com.example.androidstudy_kotlin.module

import com.example.androidstudy_kotlin.network.repository.AppRepository
import com.example.androidstudy_kotlin.BuildConfig
import com.example.androidstudy_kotlin.network.interceptor.RequestInterceptor
import com.example.androidstudy_kotlin.network.service.AppService
import com.example.androidstudy_kotlin.view.viewmodel.AreaViewModel
import com.example.androidstudy_kotlin.view.viewmodel.TestViewModel
import com.example.androidstudy_kotlin.view.viewmodel.TripDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * AppModule 설정
 */
var networkModule = module {
    single {
        createWebService<AppService>(
            okHttpClient = get(),
            baseUrl = "http://api.visitkorea.or.kr"
        )
    }

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor(get()))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
    }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String ): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

var repositoryModule = module {
    factory { AppRepository(appService = get()) }
}

var viewModelModule = module {
    viewModel { TestViewModel(appRepository = get()) }
    viewModel { (areaCode: Int, contentTypeId: Int?) -> AreaViewModel(appRepository = get(), areaCode, contentTypeId) }
    viewModel { TripDetailViewModel(appRepository = get()) }
}

//var networkModule = module {
//    single {
//        Retrofit.Builder()
////            .baseUrl("http://apis.data.go.kr")
//            .baseUrl("http://api.visitkorea.or.kr")
//            .addConverterFactory(GsonConverterFactory.create())
////            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(get())
//            .build()
//    }
//
//    single {
//        OkHttpClient().newBuilder()
//            .addInterceptor(RequestInterceptor(get()))
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
//                }
//            )
//            .build()
//    }
//}
//
//var repositoryModule = module {
//    single { AppRepository(get()) }
//}
//
//var viewModelModule = module {
//    single { BaseViewModel() }
//    factory { AreaViewModel(get()) }
//}