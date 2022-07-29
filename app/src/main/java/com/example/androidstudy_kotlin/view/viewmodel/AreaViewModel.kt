package com.example.androidstudy_kotlin.view.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidstudy_kotlin.data.model.Item
import com.example.androidstudy_kotlin.data.remote.dto.Body
import com.example.androidstudy_kotlin.data.remote.dto.Dto
import com.example.androidstudy_kotlin.data.AppRepository
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class AreaViewModel (private val appRepository: AppRepository) : BaseViewModel() {

    var subwayInfo = MutableLiveData<Dto<Body>>()

    // PagingData 요청(캐시도 가능)
    fun getTrainInfoPaging(): Flow<PagingData<Item>> {
        val param = HashMap<String, String>().apply {
            put("serviceKey", "nxD3Vj9Pl5I+JWTkXyufXja0SKBR7Idx5Lh34+fdeAuXLX06TbVgtXuqucPHXiUUebvJ19O9N5pHdOA6K976ZQ==")
            put("pageNo", "1")
            put("numOfRows", "10")
            put("_type", "json")
            put("subwayStationName", "")
        }

        return appRepository.getTrainInfoPaing(param)
    }

    private var _arrange = MutableLiveData("P")

    fun setArrange(arrange: String) {
        _arrange.value = arrange
    }

    fun getAreaInfoPaging(areaCode: Int, contentTypeId: Int?): Flow<PagingData<Item>> {
        return appRepository.getAreaInfoPaging(areaCode, _arrange.value!!, contentTypeId).cachedIn(viewModelScope)
    }
}