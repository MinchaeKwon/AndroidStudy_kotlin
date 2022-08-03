package com.example.androidstudy_kotlin.data.remote.service

import com.example.androidstudy_kotlin.data.remote.dto.Dto
import com.example.androidstudy_kotlin.data.remote.dto.Body
import com.google.gson.JsonObject
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