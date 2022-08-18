package com.example.androidstudy_kotlin.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudy_kotlin.network.repository.AppRepository
import com.example.androidstudy_kotlin.network.data.Body
import com.example.androidstudy_kotlin.network.data.Dto
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import kotlinx.coroutines.launch

class TestViewModel(private val appRepository: AppRepository) : BaseViewModel() {
    // 2. View Binding, Data Binding + ViewModel
    private var num1 = 0

    fun increase1() {
        num1++
    }

    fun getNum1(): String {
        return num1.toString();
    }
//    fun getNum1(): String = num.toString()
//    fun getNum1() = num.toString()


    // 3. View Binding, Data Binding + ViewModel + LiveData
    private var num2 = MutableLiveData<Int>()
    // private var num2 = MutableLiveData<Int>(0);

    init {
        num2.value = 0
    }

    fun increase2() {
        num2.value = num2.value?.plus(1) // num2가 null이면 plus() 메소드호출이 무시되고 아니면 메소드 정상 실행
    }

    fun getNum2(): MutableLiveData<Int> {
        return num2
    }

    val list = MutableLiveData<Dto<Body>>()

    fun getAreaInfoTest(areaCode: Int, contentTypeId: Int?) {
        val param = HashMap<String, String>().apply {
            put("ServiceKey", "8j34mk+s1/ndx0AkafC8kxGknHpk3HTehopMk9PIig4trbdhrG6PslyubpYwy4UWaU0GpUrcAwAvDsVWJkLi8g==")
            put("pageNo", "1")
            put("numOfRows", "5")
            put("areaCode", "$areaCode")
            put("arrange", "P")
            if (contentTypeId != null) put("contentTypeId", "$contentTypeId")
            put("_type", "json")
            put("MobileApp", "AndroidStudy")
            put("MobileOS", "AND")
        }

        viewModelScope.launch(exceptionHandler) {
            setLoading(true)

            val response = appRepository.getAreaInfo(param)
            if (response.isSuccessful) {
                list.postValue(response.body())
            } else {
                setError(response.code(), response.message(), null)
            }

            setLoading(false)
        }
    }
}