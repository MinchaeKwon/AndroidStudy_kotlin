package com.example.androidstudy_kotlin.data.remote.dto

import com.example.androidstudy_kotlin.data.model.Items

data class Body(
    var pageNo: Int,
    var totalCount: Int,
    var numOfRows: Int,
    var items: Items
)