package com.ediya.coupon.view.observer

import androidx.lifecycle.Observer

/**
 * [onEventUnhandledContent]는 [Event]의 내용이 처리되지 않은 경우만(only) 호출
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}