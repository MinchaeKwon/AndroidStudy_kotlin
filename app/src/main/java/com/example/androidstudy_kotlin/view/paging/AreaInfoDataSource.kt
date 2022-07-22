package com.example.androidstudy_kotlin.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidstudy_kotlin.data.AppRepository
import com.example.androidstudy_kotlin.data.model.Item

class AreaInfoDataSource(private val appRepository: AppRepository,
                         private val areaCode: Int,
                         private val arrange: String,
                         private val contentTypeId: Int?
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: 1

            val param = HashMap<String, String>().apply {
                put("serviceKey", "8j34mk+s1/ndx0AkafC8kxGknHpk3HTehopMk9PIig4trbdhrG6PslyubpYwy4UWaU0GpUrcAwAvDsVWJkLi8g==")
                put("pageNo", "$page")
                put("numOfRows", "20")
                put("areaCode", "$areaCode")
                put("arrange", arrange)
                put("contentTypeId", "$contentTypeId")
                put("_type", "json")
            }

            val response = appRepository.getTrainInfo(param)
            val list = response.body()!!.response.body.items.item

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (list.isEmpty()) null else page + 1

            LoadResult.Page(data = list, nextKey = nextKey, prevKey = prevKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}