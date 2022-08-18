package com.example.androidstudy_kotlin.network.data

data class Item(
    var subwayRouteName: String,
    var subwayStationId: String,
    var subwayStationName: String,

    val areacode: Int?,
    val cat1: String?,
    val cat2: String?,
    val cat3: String?,
    val contentid: Int?,
    val contenttypeid: Int?,
    val createdtime: Long?,
    val firstimage: String?,
    val firstimage2: String?,
    val mapx: Double?,
    val mapy: Double?,
    val mlevel: Int?,
    val modifiedtime: Long?,
    val readcount: Int?,
    val sigungucode: Int?,
    var title: String?,
    val addr1: String?,
    val overview: String? // 상세 정보 가져올 때 사용
)