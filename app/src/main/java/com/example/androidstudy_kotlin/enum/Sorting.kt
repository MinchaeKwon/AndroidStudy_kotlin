package com.example.androidstudy_kotlin.enum

enum class Sorting(val options: String, val optionValue: String) {
    OPTION_01("제목순", "O"),
    OPTION_02("조회순", "P"),
    OPTION_03("수정일순", "Q"),
    OPTION_04("생성일순", "R");

    override fun toString(): String {
        return options
    }

    companion object {
        fun from(type: String?): Sorting = values().find { it.options == type } ?: OPTION_02
    }
}