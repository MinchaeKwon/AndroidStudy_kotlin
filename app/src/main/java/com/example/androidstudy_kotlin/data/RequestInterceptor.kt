package com.example.androidstudy_kotlin.data

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-Type", "application/json;charset=UTF-8")

        val response = chain.proceed(builder.build())

        //        return chain.proceed(builder.build())
        return response
    }
}