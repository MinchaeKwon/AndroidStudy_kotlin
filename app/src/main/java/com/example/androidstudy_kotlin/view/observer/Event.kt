package com.ediya.coupon.view.observer

import com.ediya.coupon.util.LogUtil

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) { // 이벤트가 이미 처리된 경우
            null                     // 널값 반환
        } else {                     // 이벤트가 처리된 적이 없는 경우
            hasBeenHandled = true    // 재사용 방지 처리
            content                  // 값을 반환
        }
    }

    /**
     * 이벤트의 처리 여부에 상관 없이 값을 반환
     */
    fun peekContent(): T = content
}