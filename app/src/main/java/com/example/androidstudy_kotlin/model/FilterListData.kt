package com.example.androidstudy_kotlin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterListData<E : Enum<E>>(
    val title: String,
    val option: Class<E>,
    val isSelected: E? = null
): Parcelable