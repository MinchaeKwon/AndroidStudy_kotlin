package com.example.androidstudy_kotlin.network.data.event.response

data class EventData(
    var endRow: Int,
    var hasNextPage: Boolean,
    var hasPreviousPage: Boolean,
    var isFirstPage: Boolean,
    var isLastPage: Boolean,
    var list: List<Event>,
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

data class Event(
    var attachPath: String = "",      //첨부 이미지 경로
    var boardSt: String = "",         //게시 상태 코드
    var boardEventTp: String = "",    // 이벤트 유형 (코드 앞 세자리 208 : 이벤트, 아닌경우 이디야스토리, 208004 : E드림)
    var endDt: String = "",           //행사 종료일
    var couponYn: String = "",        //쿠폰 행사 여부
    var equestYn: String = "",        //EQUEST 행사 여부
    var eventAttendYn: String = "",   //이벤트 참여 여부 (진행중인 이벤트는 참여한, 종료중일 때에는 참여완료 여부)
    var giveawayYn: String = "",      //경품 행사 여부
    var id: Int,                      //고유 번호
    var isNewTag: String = "",        //new 태그 노출 여부
    var startDt: String = "",         //행사 시작일
    var stampYn: String = "",         //스탬프 행사 여부
    var title: String = "",           //제목
    var topYn: Int = 0,               //상단 이미지 노출 여부
    var eventSt: String = "",         //이벤트 상태 ing / end
    var preYn: String = "",           //당첨자 발표여부
    var joinYn: String = "",          //참여완료 태그 노출 여부
)

data class EventBadge( //노출 순서 : 경품 > 쿠폰 > 스탬프
    var isEQuest: Boolean = false,
    var isGiveaway: Boolean = false,
    var isCoupon: Boolean = false,
    var isStamp: Boolean = false,
    var isNew: Boolean = false,
    var isWinner: Boolean = false
)