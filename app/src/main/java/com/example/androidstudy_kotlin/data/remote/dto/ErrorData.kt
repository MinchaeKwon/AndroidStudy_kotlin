package com.example.androidstudy_kotlin.data.remote.dto

/**
 * Response error 데이타.
 */
data class ErrorData(
    var code: Int,
    var message: String?,
    var throwable: Throwable?,
)