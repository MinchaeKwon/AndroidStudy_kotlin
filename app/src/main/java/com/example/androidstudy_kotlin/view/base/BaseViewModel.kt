package com.example.androidstudy_kotlin.view.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidstudy_kotlin.network.data.ErrorData
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun setLoading(setLoading: Boolean) {
        _isLoading.value = setLoading
    }

    private val _exception = MutableLiveData<ErrorData>()
    val exception: LiveData<ErrorData> get() = _exception

    open fun setError(code: Int, message: String?, throwable: Throwable?) {
        _exception.value = ErrorData(code, message, throwable)
    }

//        private val _fetchState = MutableLiveData<FetchState>()
//        val fetchState : LiveData<FetchState>
//            get() = _fetchState

    protected val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
        throwable.printStackTrace()

        if (throwable is SocketException) {
            Log.e("minchae", "bad internet")
        }

        if (throwable is HttpException) {
            Log.e("minchae", "parse error")
        }

        if (throwable is UnknownHostException) {
            Log.e("minchae", "no internet or your base url is wrong")
        }

        // TODO : 에러 code 별로 정리.

        //        when(throwable){
        //            is SocketException -> _fetchState.postValue(FetchState.BAD_INTERNET)
        //            is HttpException -> _fetchState.postValue(FetchState.PARSE_ERROR)
        //            is UnknownHostException -> _fetchState.postValue(FetchState.WRONG_CONNECTION)
        //            else -> _fetchState.postValue(FetchState.FAIL)
        //        }
        setLoading(false)
    }

//    fun restApiLaunch(block: CoroutineScope.() -> Unit): Job {
//        return viewModelScope.launch(exceptionHandler) {
//            setLoading(true)
//            block()
//            setLoading(false)
//        }
//    }
}