package com.example.androidstudy_kotlin.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidstudy_kotlin.data.AppRepository
import com.example.androidstudy_kotlin.data.model.Item

class SubwayInfoDataSource(private val appRepository: AppRepository) : PagingSource<Int, Item>() {
    // 스와이프 해서 refresh나 데이터 업데이트 등 현재 목록을 대체할 새 데이터를 로드할 때 사용
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        // anchorPosition : 가장 최근에 접근한 인덱스 -> 이걸 중심으로 주변 데이터를 다시 로드
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // 사용자가 스크롤 할 때마다 데이터를 비동기적으로 가져옴
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: 1

            val param = HashMap<String, String>().apply {
                put("serviceKey", "nxD3Vj9Pl5I+JWTkXyufXja0SKBR7Idx5Lh34+fdeAuXLX06TbVgtXuqucPHXiUUebvJ19O9N5pHdOA6K976ZQ==")
                put("pageNo", "$page")
                put("numOfRows", "10")
                put("_type", "json")
                put("subwayStationName", "")
            }

            val response = appRepository.getTrainInfo(param)
            val list = response.body()!!.response.body.items.item

            // val nextKey = if (list.isEmpty() || response.body()!!.response.body.totalCount) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (list.isEmpty()) null else page + 1

            LoadResult.Page(data = list, nextKey = nextKey, prevKey = prevKey) // response 성공시 반환하는 것
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e) // response 실패시 반환하는 것
        }
    }


}