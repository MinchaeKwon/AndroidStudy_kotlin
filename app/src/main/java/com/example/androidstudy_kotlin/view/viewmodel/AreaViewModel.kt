package com.example.androidstudy_kotlin.view.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidstudy_kotlin.network.data.Item
import com.example.androidstudy_kotlin.network.data.Body
import com.example.androidstudy_kotlin.network.data.Dto
import com.example.androidstudy_kotlin.network.repository.AppRepository
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// 생성자에 의존성 주입
class AreaViewModel(
    private val appRepository: AppRepository,
    private val areaCode: Int,
    private val contentTypeId: Int?
) : BaseViewModel() {

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

    var list = MutableLiveData<PagingData<Item>>()
    private var _arrange = MutableLiveData("P")

    fun setArrange(arrange: String) {
        _arrange.value = arrange
    }

//    var list = Pager(PagingConfig(pageSize = 10)) {
//        AreaInfoDataSource(appRepository, areaCode, _arrange.value!!, contentTypeId)
//    }.flow.cachedIn(viewModelScope)

    fun getAreaInfoPaging(): Flow<PagingData<Item>> {
        return appRepository.getAreaInfoPaging(areaCode, _arrange, contentTypeId)
    }

    fun getAreaInfoPaging2() {
        viewModelScope.launch(exceptionHandler) {
            setLoading(true)

            val result = appRepository.getAreaInfoPaging(areaCode, _arrange, contentTypeId).cachedIn(viewModelScope)
            result.collect { data -> list.postValue(data) } // collectLatest도 사용 가능

            setLoading(false)
        }
    }

}