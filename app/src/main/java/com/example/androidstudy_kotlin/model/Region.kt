package com.example.androidstudy_kotlin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Region(
    val areaCode: String,
    val areaName: String
): Parcelable