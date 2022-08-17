package com.example.androidstudy_kotlin.util.extension

import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.example.androidstudy_kotlin.PracticeApplication

fun Int.dpToPx(): Int = (this * PracticeApplication.context!!.resources.displayMetrics.density).toInt()

// 180도 회전
fun rotateFilterArrow(isDefault: Boolean, view: View) {
    val anim = if (isDefault) RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    else RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    anim.duration = 250
    anim.interpolator = LinearInterpolator()
    anim.fillAfter = true
    view.startAnimation(anim)
}