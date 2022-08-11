package com.example.androidstudy_kotlin.data.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

// BindingAdapter는 메모리상에 올려서 사용해야 하기 때문에 Object로 생성
object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadRoundCornerImage")
    fun loadRoundCornerImage(imageView: ImageView, path: String?) {
        if (path.isNullOrEmpty()) {
            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
        } else {
            imageView.load(path) {
                crossfade(true)
                transformations(RoundedCornersTransformation())
                placeholder(android.R.drawable.ic_menu_gallery)
            }
        }
    }
}

// 코틀린의 확장 함수를 사용한 것 -> @JvmStatic 붙이지 않아도 됨, object로 감싸지 않아도 됨
//@BindingAdapter("loadRoundCornerImage")
//fun ImageView.loadRoundCornerImage(path: String?) {
//    if (path.isNullOrEmpty()) {
//        setImageResource(android.R.drawable.ic_menu_gallery)
//    } else {
//        load(path) {
//            crossfade(true)
//            transformations(RoundedCornersTransformation())
//            placeholder(android.R.drawable.ic_menu_gallery)
//        }
//    }
//}