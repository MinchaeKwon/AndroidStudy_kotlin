package com.example.androidstudy_kotlin.network.service

import com.ediya.coupon.network.data.SingleResult
import com.example.androidstudy_kotlin.network.data.Dto
import com.example.androidstudy_kotlin.network.data.Body
import com.example.androidstudy_kotlin.network.data.ItemPic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import com.example.androidstudy_kotlin.network.data.ResponseData
import com.example.androidstudy_kotlin.network.data.banner.BannerData
import com.example.androidstudy_kotlin.network.data.main.ActivateMenu
import com.example.androidstudy_kotlin.network.data.main.MainData
import retrofit2.http.Query

interface AppService {

    @GET("/1613000/SubwayInfoService/getKwrdFndSubwaySttnList")
    suspend fun trainInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>

    @GET("/openapi/service/rest/KorService/areaBasedList")
    suspend fun areaInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>

    @GET("/openapi/service/rest/KorService/detailCommon")
    suspend fun tripDetailInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>

    // PicSum
    @GET("/v2/list")
    suspend fun picSumList(@QueryMap query: HashMap<String, String>): Response<List<ItemPic>>

    @GET(UrlSpec.Api.main)
    suspend fun main(): Response<ResponseData<MainData>>

    @GET(UrlSpec.Api.dashboard)
    suspend fun dashboard(): Response<ResponseData<MainData>>

    @GET(UrlSpec.Api.bannerList)
    suspend fun bannerList(@Query("bannerCd") bannerCd: String): Response<ResponseData<BannerData>>

    @GET(UrlSpec.Api.registerPromotion)
    suspend fun registerPromotion(@QueryMap query: HashMap<String, String>): Response<SingleResult>

    @GET(UrlSpec.Api.activateMenu)
    suspend fun activateMenu(): Response<ResponseData<ActivateMenu>>
}