package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.model.Region
import com.example.androidstudy_kotlin.databinding.FragmentMainBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnViewBinding.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_viewFragment)
            }

            btnDataBinding.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_dataFragment)
            }

            btnRoom.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_roomFragment)
            }

            btnPaging.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToAreaTabFragment(Region("1", "서울"))
                findNavController().navigate(action)
//                findNavController().navigate(R.id.action_mainFragment_to_areaTabFragment)
            }

            btnTmap.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_tmapFragment)
            }

            btnMotion.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_motionFragment)
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }
}