package com.ediya.coupon.view.observer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ediya.coupon.util.LogUtil

/**
 * Event Wrapper class
 */
abstract class EventLiveData<T> {

    private val liveData = MutableLiveData<Event<T>>()

    protected constructor()

    protected constructor(value: T) {
        liveData.value = Event(value)
    }

    protected open fun setValue(value: T) {
        liveData.value = Event(value)
    }

    protected open fun postValue(value: T) {
        liveData.postValue(Event(value))
    }

    fun getValue() = liveData.value?.peekContent()

    fun observe(owner: LifecycleOwner, onResult: (T) -> Unit) {
        //등록된 여러 옵저버중 하나의 옵저버에서만 getContentIfNotHandled() 메서드 사용할 수 있고, 나머지는 peekContent()로 값을 받음
        /*liveData.observe(owner) {
            LogUtil.e("event handled before -> hasBeenHandled: ${it.hasBeenHandled}")
            it.getContentIfNotHandled()?.let(onResult)
            LogUtil.e("event handled after -> hasBeenHandled: ${it.hasBeenHandled}")
        }*/

        //다음과 같이 Observer 를 한번더 래핑하여 여러 옵저버를 등록해도 모두 값의 변경을 받을수 있도록 수정
        liveData.observe(owner, EventObserver {
            onResult(it)
        })
    }

    fun observePeek(owner: LifecycleOwner, onResult: (T) -> Unit) {
        liveData.observe(owner) { onResult(it.peekContent()) }
    }

}