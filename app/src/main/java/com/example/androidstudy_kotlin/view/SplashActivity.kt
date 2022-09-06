package com.example.androidstudy_kotlin.view

import android.content.Intent
import android.os.Bundle
import com.example.androidstudy_kotlin.databinding.ActivitySplashBinding
import com.example.androidstudy_kotlin.util.extension.hideSystemUI
import com.example.androidstudy_kotlin.view.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()

        val intent= Intent(this, MainActivity::class.java)
        val t: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        t.start()
    }
}