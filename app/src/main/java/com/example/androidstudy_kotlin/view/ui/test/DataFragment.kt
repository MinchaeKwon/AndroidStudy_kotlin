package com.example.androidstudy_kotlin.view.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentDataBinding
import com.example.androidstudy_kotlin.view.viewmodel.TestViewModel

// Data Binding 사용
class DataFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentDataBinding

    private var num = 0

    private val viewModel: TestViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.btnPrev.setOnClickListener {
            navController.navigate(R.id.action_dataFragment_to_mainFragment)
        }

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