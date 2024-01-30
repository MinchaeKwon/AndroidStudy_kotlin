package com.example.androidstudy_kotlin.network.data.edream.response

data class EDreamDashboard(
    var boardImgPath: String = "",              // 판 이미지 경로
    var canGiftNormalStampCnt: Int = 0,         // 선물 가능한 일반스탬프 개수
    var canGiftSpecialStampCnt: Int = 0,        // 선물 가능한 지정스탬프 개수
    var eDreamEventId: Int = 0,                 // 진행중인 E-드림과 연관된 이벤트 ID
    var eDreamId: Int = 0,                      // 진행중인 E-드림 ID
    var normalImgPath: String = "",             // 일반스탬프 이미지 경로
    var normalStampCnt: Int = 0,                // 보유한 일반스탬프 개수
    var specialImgPath: String = "",            // 지정스탬프 이미지 경로
    var specialStampCnt: Int = 0,               // 보유한 지정스탬프 개수
    var textImgPath: String = "",               // 텍스트 이미지 경로
    var todayGiftNormalStampCnt: Int = 0,       // 오늘 선물한 일반스탬프 개수
    var todayGiftSpecialStampCnt: Int = 0,      // 오늘 선물한 지정스탬프 개수
)