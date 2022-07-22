package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.enum.Area
import com.example.androidstudy_kotlin.data.model.Region
import com.example.androidstudy_kotlin.databinding.FragmentAreaTabBinding
import com.example.androidstudy_kotlin.view.adapter.AreaListPagerAdapter
import com.example.androidstudy_kotlin.view.adapter.SubwayInfoPagingAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class AreaTabFragment : BaseFragment<FragmentAreaTabBinding>() {
    private val args: AreaTabFragmentArgs by navArgs()
    private var mSelectedType: Area = Area.OPTION_01

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabTitle = resources.getStringArray(R.array.tab_title)
        val areaArr = resources.getStringArray(R.array.area)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, areaArr)

        // args가 null이 아닐 때만 동작
        args.region?.let {
            binding.apply {
                spArea.adapter = spinnerAdapter // spinner adapter 연결

                // spinner 항목 선택시 동작
                spArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        mSelectedType = Area.from(spArea.selectedItem.toString())

                        val action = AreaTabFragmentDirections.actionAreaTabFragmentSelf()
//                        action.region = Region(mSelectedType.areaCode.toString(), mSelectedType.areaName)
                        findNavController().navigate(action)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }

                vp2Tab.adapter = AreaListPagerAdapter(1, childFragmentManager, lifecycle)

                TabLayoutMediator(tlTab, vp2Tab) { tab, position ->
                    tab.text = tabTitle[position]
                }.attach()

                // 이전 버튼 클릭
                btnTabBack.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }


    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaTabBinding {
        return FragmentAreaTabBinding.inflate(inflater, container, false)
    }
}