package com.example.androidstudy_kotlin.network.data

/**
 * Response result Data.
 */
data class ResponseData<T>(
    var result: ResultData, // 결과값
    var data: T             // Data
)