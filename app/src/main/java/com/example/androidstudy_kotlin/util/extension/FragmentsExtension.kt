package com.example.androidstudy_kotlin.util.extension

import androidx.fragment.app.Fragment

fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return
    activity?.runOnUiThread(action)
}