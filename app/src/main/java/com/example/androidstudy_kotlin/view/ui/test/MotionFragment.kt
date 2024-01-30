package com.example.androidstudy_kotlin.view.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidstudy_kotlin.databinding.FragmentMotionBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment

class MotionFragment : BaseFragment<FragmentMotionBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMotionBinding {
        return FragmentMotionBinding.inflate(inflater, container, false)
    }
}