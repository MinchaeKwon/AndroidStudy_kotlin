package com.example.androidstudy_kotlin.util.extension

import com.example.androidstudy_kotlin.PracticeApplication

fun Int.dpToPx(): Int = (this * PracticeApplication.context!!.resources.displayMetrics.density).toInt()