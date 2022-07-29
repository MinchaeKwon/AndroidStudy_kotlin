package com.example.androidstudy_kotlin.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudy_kotlin.data.AppRepository
import com.example.androidstudy_kotlin.data.model.Item
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

class TripDetailViewModel (private val appRepository: AppRepository) : BaseViewModel() {

    var list = MutableLiveData<Item>()

    fun getTripDetailInfo(contentId: Int, contentTypeId: Int) {
        viewModelScope.launch(exceptionHandler) {
            setLoading(true)

            val param = HashMap<String, String>().apply {
                put("ServiceKey", "8j34mk+s1/ndx0AkafC8kxGknHpk3HTehopMk9PIig4trbdhrG6PslyubpYwy4UWaU0GpUrcAwAvDsVWJkLi8g==")
                put("pageNo", "1")
                put("numOfRows", "1")
                put("contentId", "$contentId")
                put("contentTypeId", "$contentTypeId")
                put("defaultYN", "Y")
                put("defaultYN", "Y")
                put("firstImageYN", "Y")
                put("catcodeYN", "Y")
                put("addrinfoYN", "Y")
                put("mapinfoYN", "Y")
                put("overviewYN", "Y")
                put("_type", "json")
                put("MobileApp", "AndroidStudy")
                put("MobileOS", "AND")
            }

            val response = appRepository.getTripDetailInfo(param)
            if (response.isSuccessful) {
                val result = response.body()?.getAsJsonObject("response")?.
                        getAsJsonObject("body")?.
                        getAsJsonObject("items")?.
                        getAsJsonObject("item")

                list.postValue(Gson().fromJson(result, Item::class.java))
            } else {
                setError(response.code())
            }

            setLoading(false)
        }
    }
}