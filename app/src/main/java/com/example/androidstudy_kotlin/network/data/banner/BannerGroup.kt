package com.example.androidstudy_kotlin.network.data.banner

data class AllBannerData(
    var list: List<BannerGroup>         //그룹별 배너 목록
)

data class BannerData(
    var list: List<BannerGroup.Banner>  //배너 목록
)

data class BannerGroup(
    var bannerCd: String,               //배너코드 (Constants 참고)
    var bannerList:	List<Banner>        //배너 목록
) {
    data class Banner(
        var bannerCd: String = "",      //배너코드 (Constants 참고)
        var id: Int = 0,                //배너 고유 번호
        var imgPath: String = "",       //이미지 경로
        var link: String = "",          //링크
        var linkType: String = "",      //링크 구분 코드 (Constants 참고)
        var title: String = "",         //배너 제목
    )
}