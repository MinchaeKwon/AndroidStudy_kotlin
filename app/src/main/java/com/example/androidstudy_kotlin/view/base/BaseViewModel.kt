package com.example.androidstudy_kotlin.view.base

import androidx.lifecycle.ViewModel
import com.ediya.coupon.util.LogUtil
import com.ediya.coupon.view.observer.EventLiveData
import com.ediya.coupon.view.observer.MutableEventLiveData
import com.example.androidstudy_kotlin.network.data.ErrorData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val _isLoading = MutableEventLiveData<Boolean>()
    val isLoading: EventLiveData<Boolean> get() = _isLoading

    fun setLoading(setLoading: Boolean) {
        _isLoading.setValue(setLoading)
    }

    private val _exception = MutableEventLiveData<ErrorData>()
    val exception: EventLiveData<ErrorData> get() = _exception

    open fun setError(code: Int?, message: String?) {
        _exception.setValue(ErrorData(code, message))
    }

    open fun setError(errorData: ErrorData) {
        _exception.setValue(errorData)
    }

    // Coroutine's background job
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        LogUtil.d("BaseViewModel onCleared()")

        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }
}