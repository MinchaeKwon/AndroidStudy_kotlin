package com.example.androidstudy_kotlin.network.service

import com.example.androidstudy_kotlin.network.data.Dto
import com.example.androidstudy_kotlin.network.data.Body
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AppService {

    @GET("/1613000/SubwayInfoService/getKwrdFndSubwaySttnList")
    suspend fun trainInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>

    @GET("/openapi/service/rest/KorService/areaBasedList")
    suspend fun areaInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>

    @GET("/openapi/service/rest/KorService/detailCommon")
    suspend fun tripDetailInfo(@QueryMap query: HashMap<String, String>): Response<Dto<Body>>
}