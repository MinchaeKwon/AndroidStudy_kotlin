package com.example.androidstudy_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.androidstudy_kotlin.databinding.ActivityDataBinding
import com.example.androidstudy_kotlin.viewmodel.MainViewModel

// Data Biding 사용
class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private var num = 0

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data)

//        binding.counter1 = this // 현재 binding시킨 xml의 variable name

//        binding.txtNum.text = num.toString() // 1. Data Binding
//        binding.txtNum.text = viewModel.getNum1(); // 2. Data Binding + ViewModel

        // 3. Data Binding + ViewModel + LiveData
        binding.lifecycleOwner = this // binding에 LifeCycleOwner을 지정해줘야 LiveData가 실시간으로 변화함
        binding.counter2 = viewModel


//        // binding 버튼 클릭 이벤트
//        binding.btnIncrease.setOnClickListener {
//            text = "Binding"
//            // Data가 변동될 경우 binding된 View들에 Data 변화를 알려줌
//            binding.invalidateAll()
//        }
//
//        // LiveData의 value의 변경을 감지하고 호출
//        // 첫 번째 매개변수인 this는 LifeCycleOwner인 MainActivity
//        // 두 번째 매개변수인 Observer Callback은 LiveData(liveText)의 value의 변경을 감지하고 호출되는 부분
//        liveText.observe(this, Observer {
//
//        })

        binding.btnPrev.setOnClickListener {
            finish()
        }
    }

    fun increase() {
        // 1. Data Binding
//        num++
//        binding.txtNum.text = num.toString()

        // 2. Data Binding + ViewModel
//            viewModel.increase1()
//            binding.txtNum.text = viewModel.getNum1()
    }
}