package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentViewBinding
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel

// View Binding 사용
class ViewFragment : Fragment() {

    private lateinit var navController: NavController

    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    private var num = 0

    // 2. View Binding + ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.btnPrev.setOnClickListener {
            navController.navigate(R.id.action_viewFragment_to_mainFragment)
        }

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

        viewModel.getNum2().observe(viewLifecycleOwner, Observer { number ->
            binding.txtNum.text = number.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}