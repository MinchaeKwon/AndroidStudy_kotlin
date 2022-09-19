package com.example.androidstudy_kotlin.common

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class AutoScrollHorizontalListLayoutManager(val context: Context) : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State, position: Int) {
        val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(context) {
            private val SPEED = 1103f // Change this value (default=25f)

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return SPEED / displayMetrics.densityDpi
            }
        }

        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }
}