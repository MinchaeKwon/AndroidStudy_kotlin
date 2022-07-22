package com.example.androidstudy_kotlin.data.model

import android.os.Parcelable
import androidx.navigation.NavDirections
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Region(
    val areaCode: String,
    val areaName: String
): Parcelable