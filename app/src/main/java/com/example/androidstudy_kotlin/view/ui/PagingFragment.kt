package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentPagingBinding
import com.example.androidstudy_kotlin.view.adapter.SubwayInfoPagingAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel

class PagingFragment : BaseFragment<FragmentPagingBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private val infoAdapter = SubwayInfoPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvPaging.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPagingBinding {
        return FragmentPagingBinding.inflate(inflater, container, false)
    }
}