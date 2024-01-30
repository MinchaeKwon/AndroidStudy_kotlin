package com.ediya.coupon.module

import android.text.TextUtils
import com.example.androidstudy_kotlin.BuildConfig
import com.example.androidstudy_kotlin.PracticeApplication
import com.example.androidstudy_kotlin.network.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var instance: Retrofit? = null
    fun getInstance(apiDomain: String?): Retrofit {

        val apiUrl = if(TextUtils.isEmpty(apiDomain)) {
//            BuildConfig.URL_API_DOMAIN
            "https://memapp.ediya.com/"
        } else {
            apiDomain
        }

        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor(PracticeApplication.appApplication))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        //if (instance == null) {
        instance = Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //}
        return instance!!

    }
}