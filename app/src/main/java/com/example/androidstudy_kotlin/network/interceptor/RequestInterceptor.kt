package com.example.androidstudy_kotlin.network.interceptor

import android.content.Context
import android.os.Build
import com.example.androidstudy_kotlin.BuildConfig
import com.example.androidstudy_kotlin.PracticeApplication
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-Type", "application/json;charset=UTF-8")
        builder.addHeader("DeviceId", PracticeApplication.getAndroidID()) //deivce id
        builder.addHeader("AppVersion", BuildConfig.VERSION_NAME) //app version
        builder.addHeader("OsVersion", Build.VERSION.RELEASE.toString()) //os version
        builder.addHeader("Platform", "android") //platform (android/ios)
        builder.addHeader("DeviceModel", Build.MODEL) //device model

        return chain.proceed(builder.build())
    }
}