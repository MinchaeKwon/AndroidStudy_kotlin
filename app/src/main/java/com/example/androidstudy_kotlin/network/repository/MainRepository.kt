package com.example.androidstudy_kotlin.network.repository

import com.ediya.coupon.network.data.SingleResult
import com.example.androidstudy_kotlin.network.ResponseState
import com.example.androidstudy_kotlin.network.data.ResponseData
import com.example.androidstudy_kotlin.network.data.banner.BannerData
import com.example.androidstudy_kotlin.network.data.main.ActivateMenu
import com.example.androidstudy_kotlin.network.data.main.MainData
import com.example.androidstudy_kotlin.network.service.AppService
import com.example.androidstudy_kotlin.view.base.BaseRepository

class MainRepository(private val appService: AppService) : BaseRepository() {
    suspend fun getBannerList(bannerCd: String): ResponseState<ResponseData<BannerData>> {
        return apiCallHandle { appService.bannerList(bannerCd) }
    }

    suspend fun getMain(): ResponseState<ResponseData<MainData>> {
        return apiCallHandle { appService.main() }
    }

    suspend fun getDashboard(): ResponseState<ResponseData<MainData>> {
        return apiCallHandle { appService.dashboard() }
    }

    suspend fun registerPromotion(query: HashMap<String, String>): ResponseState<SingleResult> {
        return apiCallHandle { appService.registerPromotion(query) }
    }

    suspend fun getActivateMenu(): ResponseState<ResponseData<ActivateMenu>> {
        return apiCallHandle { appService.activateMenu() }
    }
}