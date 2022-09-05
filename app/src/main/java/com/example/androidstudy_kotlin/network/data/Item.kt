package com.example.androidstudy_kotlin.network.data

data class Item(
    var subwayRouteName: String,
    var subwayStationId: String,
    var subwayStationName: String,

    val areacode: String?,
    val cat1: String?,
    val cat2: String?,
    val cat3: String?,
    val contentid: String?,
    val contenttypeid: String?,
    val createdtime: String?,
    val firstimage: String?,
    val firstimage2: String?,
    val mapx: String?,
    val mapy: String?,
    val mlevel: String?,
    val modifiedtime: String?,
    val readcount: String?,
    val sigungucode: String?,
    var title: String?,
    val addr1: String?,
    val overview: String? // 상세 정보 가져올 때 사용
)