package com.example.androidstudy_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidstudy_kotlin.data.model.Region
import com.example.androidstudy_kotlin.databinding.ActivityMainBinding
import com.example.androidstudy_kotlin.view.ui.MainFragmentDirections
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

//        navController = binding.navHostFragment.findNavController()
//        navController = nav_host_fragment.findNavController()

        navController = findNavController(R.id.nav_host_fragment)
        binding.bnvMain.setupWithNavController(navController)

//        val navInflater = navController.navInflater
//        val graph = navInflater.inflate(R.navigation.nav_graph)
//
//        val argument = NavArgument.Builder().setDefaultValue(Region("1", "서울")).build()
//        graph.addArgument("region", argument)
//        navController.graph = graph

//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when(destination.id) {
//                R.id.areaTabFragment -> {
//                    val argument = NavArgument.Builder().setDefaultValue(Region("1", "서울")).build()
//                    destination.addArgument("region", argument)
//                }
//            }
//        }
    }
}