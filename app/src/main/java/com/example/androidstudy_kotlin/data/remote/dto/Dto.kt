package com.example.androidstudy_kotlin.data.remote.dto

/**
 * Dto 기본형식
 */
data class Dto<T>(
    //    val code: Int,
    //    val message: String,
    //    val data: T

    // test subway 형식
    var response: Response<T>

) {
    companion object {
        const val SUCCESS = 1
        const val FAIL = 0
    }

    data class Response<T>(
        var header: Header,
        var body: T
    )

}