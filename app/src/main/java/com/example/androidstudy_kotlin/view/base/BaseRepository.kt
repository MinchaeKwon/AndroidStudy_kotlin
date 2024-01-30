package com.example.androidstudy_kotlin.view.base

import com.example.androidstudy_kotlin.network.ResponseState
import com.example.androidstudy_kotlin.network.data.ErrorData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketException

abstract class BaseRepository() {

    // handle api errors.
    suspend fun <T> apiCallHandle(apiToBeCalled: suspend () -> Response<T>): ResponseState<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    ResponseState.Success(data = response.body()!!)
                } else {
                    ResponseState.Error(ErrorData(response.code(), response.message() ?: "Response Error"))
                }
            } catch (e: SocketException) {
                ResponseState.Error(ErrorData(96, "SocketException Error"))
            } catch (e: HttpException) {
                ResponseState.Error(ErrorData(97, "HttpException Error"))
            } catch (e: IOException) {
                ResponseState.Error(ErrorData(98, "Network Connection Error"))
            } catch (e: Exception) {
                ResponseState.Error(ErrorData(99, "Unknown Error"))
            }
        }
    }
}