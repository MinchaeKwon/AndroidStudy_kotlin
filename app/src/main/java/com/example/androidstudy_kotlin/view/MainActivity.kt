package com.example.androidstudy_kotlin.view

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.ActivityMainBinding
import com.example.androidstudy_kotlin.view.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        hideStatusBar()



        navController = findNavController(R.id.nav_host_fragment)
        binding.bnvMain.setupWithNavController(navController)

//        navController = binding.navHostFragment.findNavController()
//        navController = nav_host_fragment.findNavController()
    }
}