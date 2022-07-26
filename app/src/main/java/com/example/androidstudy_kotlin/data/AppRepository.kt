package com.example.androidstudy_kotlin.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidstudy_kotlin.view.paging.SubwayInfoDataSource
import com.example.androidstudy_kotlin.data.model.Item
import com.example.androidstudy_kotlin.data.remote.dto.Body
import com.example.androidstudy_kotlin.data.remote.dto.Dto
import com.example.androidstudy_kotlin.data.remote.service.AppService
import com.example.androidstudy_kotlin.view.paging.AreaInfoDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit

class AppRepository(private val retrofit: Retrofit) {

    suspend fun getTrainInfo(query: HashMap<String, String>): Response<Dto<Body>> {
        return retrofit.create(AppService::class.java).trainInfo(query)
    }

    // repository에서 Pager와 PagerSource(SubwayInfoDataSource)를 이용해 PagingData로 반환 -> 여기서는 Flow 형태로 반환
    fun getTrainInfoPaing(query: HashMap<String, String>): Flow<PagingData<Item>> {
        // PagindConfig 안에 들어가는 파라미터 : pageSize 각 페이지에 로드할 데이터 수, enablePlaceHolders 플레이스 홀더 사용 여부
        return Pager(PagingConfig(pageSize = 10)) {
            SubwayInfoDataSource(this)
        }.flow
    }

    suspend fun getAreaInfo(query: HashMap<String, String>): Response<Dto<Body>> {
        Log.d("minchae", "44444444444")
        return retrofit.create(AppService::class.java).areaInfo(query)
    }

    fun getAreaInfoPaging(areaCode: Int, arrange: String, contentTypeId: Int?): Flow<PagingData<Item>> {
        Log.d("minchae", "22222222222")
        return Pager(PagingConfig(pageSize = 10)) {
            AreaInfoDataSource(this, areaCode, arrange, contentTypeId)
        }.flow
    }
}