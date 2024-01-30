package com.ediya.coupon.network.data

data class PagingInfo<T>(
    var endRow:	Int,
    var hasNextPage: Boolean,
    var hasPreviousPage: Boolean,
    var isFirstPage: Boolean,
    var isLastPage:	Boolean,
    var list: List<T>,
    var navigateFirstPage: Int,
    var navigateLastPage: Int,
    var navigatePages: Int,
    var navigatepageNums: List<Int>,
    var nextPage: Int,
    var pageNum: Int,
    var pageSize: Int,
    var pages: Int,
    var prePage: Int,
    var size: Int,
    var startRow: Int,
    var total: Int
)
