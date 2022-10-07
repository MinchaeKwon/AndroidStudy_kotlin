package com.example.androidstudy_kotlin.view.ui.custom

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import java.lang.Math.abs


class SlideTransformer(private val offscreenPageLimit: Int): ViewPager2.PageTransformer {
    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f
    }

    private val MIN_SCALE = 0.5f
    private val MAX_SCALE = 0.8f
    private val MIN_FADE = 0.7f

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth: Int = view.width
            when {
                position < -1 -> {
                    // This page is way off-screen to the left.
                    alpha = MIN_FADE
                }
                position < 0 -> {
                    ViewCompat.setTranslationZ(view, position)

                    alpha = 1 + position * (1 - MIN_FADE)
                    translationX = -pageWidth * MAX_SCALE * position

                    // 양쪽 페이지와의 길이 차이
                    scaleX = 1 - (0.15f * abs(position))
                    scaleY = 1 - (0.15f * abs(position))
                }
                position == 0f -> {
                    ViewCompat.setTranslationZ(view, 0f)

                    alpha = 1f
                    translationX = 0f
                }
                position <= 1 -> {
                    // Modify the default slide transition to shrink the page as well
                    ViewCompat.setTranslationZ(view, -position)

                    alpha = 1 - position * (1 - MIN_FADE)
                    translationX = pageWidth * MAX_SCALE * -position

                    scaleX = 1 - (0.15f * abs(position))
                    scaleY = 1 - (0.15f * abs(position))
                }
                else -> {
                    // This page is way off-screen to the right.
                    alpha = MIN_FADE
                }
            }


//            ViewCompat.setElevation(page, -abs(position))
//
//            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
//            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA
//
//            when {
//                position <= 0f -> {
//                    translationX = DEFAULT_TRANSLATION_X
//                    scaleX = DEFAULT_SCALE
//                    scaleY = DEFAULT_SCALE
//                    alpha = DEFAULT_ALPHA + position
//                }
//                position <= offscreenPageLimit - 1 -> {
//                    scaleX = scaleFactor
//                    scaleY = scaleFactor
//                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
//                    alpha = alphaFactor
//                }
//                else -> {
//                    translationX = DEFAULT_TRANSLATION_X
//                    scaleX = DEFAULT_SCALE
//                    scaleY = DEFAULT_SCALE
//                    alpha = DEFAULT_ALPHA
//                }
//            }
        }
    }
}