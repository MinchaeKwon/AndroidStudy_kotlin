package com.example.androidstudy_kotlin.view.viewmodel

import com.ediya.coupon.network.data.SingleResult
import com.ediya.coupon.view.observer.MutableEventLiveData
import com.example.androidstudy_kotlin.network.ResponseState
import com.example.androidstudy_kotlin.network.data.ResponseData
import com.example.androidstudy_kotlin.network.data.banner.BannerData
import com.example.androidstudy_kotlin.network.data.main.ActivateMenu
import com.example.androidstudy_kotlin.network.data.main.MainData
import com.example.androidstudy_kotlin.network.repository.MainRepository
import com.example.androidstudy_kotlin.view.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {
    var bannerListResult = MutableEventLiveData<ResponseState<ResponseData<BannerData>>>()
    var mainResult = MutableEventLiveData<ResponseState<ResponseData<MainData>>>()
    var dashboardResult = MutableEventLiveData<ResponseState<ResponseData<MainData>>>()
    var promotionResult = MutableEventLiveData<ResponseState<SingleResult>>()
    var activateMenu = MutableEventLiveData<ResponseState<ResponseData<ActivateMenu>>>()

    fun getBannerList(bannerCd: String) {
        launch {
            bannerListResult.postValue(ResponseState.Loading())
            bannerListResult.postValue(repository.getBannerList(bannerCd))
        }
    }

    fun getMain() {
        launch {
            mainResult.postValue(ResponseState.Loading())
            mainResult.postValue(repository.getMain())
        }
    }

    fun getDashboard() {
        launch {
            dashboardResult.postValue(ResponseState.Loading())
            dashboardResult.postValue(repository.getDashboard())
        }
    }

    fun registerPromotion(query: HashMap<String, String>) {
        launch {
            promotionResult.postValue(ResponseState.Loading())
            promotionResult.postValue(repository.registerPromotion(query))
        }
    }

    fun getActivateMenu() {
        launch {
            activateMenu.postValue(ResponseState.Loading())
            activateMenu.postValue(repository.getActivateMenu())
        }
    }
}