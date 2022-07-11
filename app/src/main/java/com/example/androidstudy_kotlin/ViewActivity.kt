package com.example.androidstudy_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.androidstudy_kotlin.databinding.ActivityViewBinding
import com.example.androidstudy_kotlin.viewmodel.MainViewModel

// View Biding 사용
class ViewActivity : AppCompatActivity() {
    // 1. View Binding
    private lateinit var binding: ActivityViewBinding
    private var num = 0

    // 2. View Binding + ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityViewBinding.inflate(layoutInflater);
        setContentView(binding.root);

        // 1. View Binding
//        binding.btnIncrease.setOnClickListener {
//            num++
//            binding.txtNum.text = num.toString()
//        }

        // 2. View Binding + ViewModel
//        binding.btnIncrease.setOnClickListener {
//            model.increase1()
//            binding.txtNum.text = model.getNum1()
//        }

        // 3. View Binding + ViewModel + LiveData
        binding.btnIncrease.setOnClickListener {
            viewModel.increase2()
        }

        viewModel.getNum2().observe(this, Observer { number ->
            binding.txtNum.text = number.toString()
        })

        binding.btnPrev.setOnClickListener {
            finish()
        }
    }
}