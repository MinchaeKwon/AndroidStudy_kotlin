package com.example.androidstudy_kotlin.network

import com.example.androidstudy_kotlin.network.data.ErrorData

sealed class ResponseState<T>(val data: T? = null, val errorData: ErrorData? = null) {

    class Success<T>(data: T) : ResponseState<T>(data = data)

    class Error<T>(errorData: ErrorData) : ResponseState<T>(errorData = errorData)

    class Loading<T>() : ResponseState<T>()
}