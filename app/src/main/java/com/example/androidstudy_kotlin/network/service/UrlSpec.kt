package com.example.androidstudy_kotlin.network.service

object UrlSpec {

    const val BASE_API_URL = "https://memapp.ediya.com/"

    const val GEOCODE_URL = "https://naveropenapi.apigw.ntruss.com/"

    object Api {

        // 대시보드
        const val dashboard = "/api/v1/main/dashboard"

        // 메인
        const val main = "/api/v1/main/main"

        // 활성화된 메뉴 조회(예약구매, E드림)
        const val activateMenu = "/api/v1/main/activateMenu"

        // 프로모션 코드 등록
        const val registerPromotion = "/api/v1/main/promotion"

        // 특정 메뉴 배너 목록 가져오기
        const val bannerList = "/api/v1/card/getBannerList"
    }
}