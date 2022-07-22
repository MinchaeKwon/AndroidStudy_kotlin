package com.example.androidstudy_kotlin.data.enum

enum class Area(val areaName: String, val areaCode: Int) {
    OPTION_01("서울", 1),
    OPTION_02("인천", 2),
    OPTION_03("대전", 3),
    OPTION_04("대구", 4),
    OPTION_05("광주", 5),
    OPTION_06("부산", 6),
    OPTION_07("울산", 7),
    OPTION_08("세종", 8),
    OPTION_09("경기도", 31),
    OPTION_10("강원도", 32),
    OPTION_11("충청북도", 33),
    OPTION_12("충청남도", 34),
    OPTION_13("경상북도", 35),
    OPTION_14("경상남도", 36),
    OPTION_15("전라북도", 37),
    OPTION_16("전라남도", 38),
    OPTION_17("제주도", 39);

    override fun toString(): String {
        return areaName.toString()
    }

    companion object {
        fun from(type: String?): Area = values().find { it.areaName == type } ?: OPTION_01
    }
}