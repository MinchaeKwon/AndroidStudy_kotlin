package com.example.androidstudy_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidstudy_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.btnViewBiding.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }

        binding.btnDataBinding.setOnClickListener {
            startActivity(Intent(this, DataActivity::class.java));
        }

        binding.btnRoom.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}