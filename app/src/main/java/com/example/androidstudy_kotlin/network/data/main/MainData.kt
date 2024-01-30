package com.example.androidstudy_kotlin.network.data.main

import com.example.androidstudy_kotlin.network.data.banner.BannerGroup
import com.example.androidstudy_kotlin.network.data.edream.response.EDreamDashboard
import com.example.androidstudy_kotlin.network.data.event.response.Event

data class MainData(
    var bottomEvents: List<Event>? = listOf(),              // 하단이벤트 목록
    var dashboard: Dashboard,
    var recommend: MainRecommend?,
    var subscribeRecommend: SubscribeRecommend?,
    var title: String? = "",                                // 그리팅 메시지
    var topBanners: List<BannerGroup.Banner>? = listOf(),   // 상단배너리스트
    var popups: List<MainPopup>? = listOf(),                // 팝업리스트
    var temps: List<Temp> = listOf(),                       // 온도 목록
    var sizes: List<Size> = listOf(),                       // 사이즈 목록
    var deliveryAllYn: Boolean? = false                     // 배달 전체매장 시행여부 (false -> 배달지 관리 내 일부매장 시행 팝업 노출, true -> 배달지 관리 내 일부매장 시행 팝업 비노출)
) {
    data class MainPopup(
        var id: Int = 0,                // 팝업아이디
        var image: String = "",         // 이미지
        var link: String = "",          // 링크
        var linkType: String = "",      // 링크타입 909001 이벤트, 909002 이디야스토리, 909003 상품, 909004 URL
        var title: String = "",         // 타이틀
    )

    data class MainRecommend(
        var keywords: List<MainRecommendKeyword> = listOf(),    // 추천타이틀내 키워드 리스트
        var title: String = "",                                 // 추천타이틀
    )

    data class MainRecommendKeyword(
        var items: List<MainRecommendItem> = listOf(),  // 키워드별 추천상품
        var keyword: String = "",                       // 키워드
        var recommendId: Int = 0,                       // 키워드아이디
    )

    data class MainRecommendItem(
        var bestYn: Int = 0,                    // 베스트상품 여부
        var deliveryCate: String = "",          // 배달 카테고리
        var edreamYn: Int = 0,                  // 이드림 여부
        var giftCate: String = "",              // 선물하기 카테고리
        var icedOnly: Int = 0,                  // 아이스만 있는상품
        var itemImage: String = "",             // 이미지
        var itemMainId: Int = 0,                // 메인아이디
        var itemName: String = "",              // 아이템이름
        var newYn: Int = 0,                     // 신상품 여부
        var pickupCate: String = "",            // 픽업 카테고리
        var price: Double = 0.0,                // 가격
        var couponId: Int = 0,                  // 선물인 경우 couponId로 조회
    )

    data class SubscribeRecommend(
        var id: Int = 0,                                        // 추천상품 ID
        var subscribeList: List<SubscribeItem> = emptyList(),   // 구독상품 목록
        var title: String = "",                                 // 추천상품 타이틀
    )

    data class SubscribeItem(
        var id: Int = 0,            // 구독상품 고유 번호
        var itemMainId: Int = 0,    // 통합상품 고유 번호
        var itemName: String = "",  // 통합상품명
        var imgPath: String = "",   // 상품이미지 (단일건 : 목록)
        var sizeCd: String? = "",   // 사이즈 종류 ( 600001 : Regular, 600002 : Extra, 600003 : Large, 600004 : Small, 600005 : Ultra )
        var rate: Int = 0,          // 할인율
        var itemPrice: Int = 0,     // 포스상품가격
        var period: Int = 0,        // 구독기간
        var canSaleCnt: Int = 0,    // 판매 가능 수량
    )

    data class Size(
        var codeId: String = "",         // 카테고리 고유 번호
        var codeName: String = "",       // 카테고리명
    )

    data class Temp(
        var codeId: String = "",         // 카테고리 고유 번호
        var codeName: String = "",       // 카테고리명
    )
}

data class Dashboard(
    var cusNickname: String = "",                       // 닉네임
    var image: String = "",                             // 사용자이미지
    var cusLevel: String = "",                          // 현재레벨
    var cusNextLevel: String = "",                      // 다음레벨
    var levelExpDate: String = "",                      // 레벨유지기간
    var levelUpStampCnt: Int = 0,                       // 레벨업스탬프 보유수
    var levelUpStampLimit: Int = 0,                     // 다음레벨 달성 스탬프
    var levelUpStampNeed: Int = 0,                      // 다음레벨까지 부족한 스탬프수
    var couponCnt: Int = 0,                             // 사용가능 쿠폰수
    var stampCnt: Int = 0,                              // 사용가능 스탬프수
    var subscribeCnt: Int = 0,                          // 이용중인 구독개수
    var notiCnt: Int = 0,                               // 미확인 알림 수
    var orderCnt: Int = 0,                              // 1개월이내 배달픽업 주문수
    var promotionCd: String = "",                       // 프로모션코드
    var promotionEvtId: Int = 0,                        // 프로모션이벤트 아이디
    var promotionYn: Int = 0,                           // 프로모션이벤트 존재 : 1, 미존재 : 0
    var subscribeSaleAmount: Int = 0,                   // 구독 할인받은 혜택금액
    var eDreamId: Int = 0,                              // 진행중인 E드림 ID (진행중인 E드림이 없을 시 0)
    var edreamStampDashboard: EDreamDashboard? = null,  // 이드림 대시보드
    var reserveId: Int = 0,                             // 진행중인 예약구매 ID (진행중인 예약구매가 없을 시 0)
    var vipTitle: String? = "",                         // vvip 타이틀명
    var vipUri: String? = "",                           // vvip 이벤트 상세 페이지 연결용 EVENT ID
    var vipYn: Int = 0,                                 // vvip 여부 - 노출 : 1, 미노출 : 0
)

data class ActivateMenu(
    var eDreamEventId: Int = 0,         // 진행중인 E-드림과 연관된 이벤트 ID
    var eDreamId: Int = 0,              // 진행중인 E드림 ID (진행중인 E드림이 없을 시 0)
    var reserveId: Int = 0,             // 진행중인 예약구매 ID (진행중인 예약구매가 없을 시 0)
)