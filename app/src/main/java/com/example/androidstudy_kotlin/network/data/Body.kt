package com.example.androidstudy_kotlin.network.data

import com.example.androidstudy_kotlin.network.data.Items

data class Body(
    var pageNo: Int,
    var totalCount: Int,
    var numOfRows: Int,
    var items: Items
)