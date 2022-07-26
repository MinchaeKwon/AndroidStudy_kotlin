package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.util.Log
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

        binding.apply {
            // 이전 버튼 클릭
            btnTabBack.setOnClickListener {
                findNavController().popBackStack()
            }

            spArea.adapter = spinnerAdapter // spinner adapter 연결

            // spinner 항목 선택시 동작 -> 지역 선택
            spArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("minchae", "spinner 선택 : " + spArea.selectedItem.toString())

                    mSelectedType = Area.from(spArea.selectedItem.toString())

                    val action = AreaTabFragmentDirections.actionAreaTabFragmentSelf(Region(mSelectedType.areaCode.toString(), mSelectedType.areaName))
//                        action.region = Region(mSelectedType.areaCode.toString(), mSelectedType.areaName) // 에러 발생
                    findNavController().navigate(action)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

            // args가 null이 아닐 때만 동작
            args.region?.let {
                Log.d("minchae", "viewpager 사용")

                // viewpager2 사용
                vp2Tab.adapter = AreaListPagerAdapter(it.areaCode.toInt(), childFragmentManager, lifecycle)

                // 탭 추가
                TabLayoutMediator(tlTab, vp2Tab) { tab, position ->
                    Log.d("minchae", "탭 추가")
                    tab.text = tabTitle[position]
                }.attach()
            }
        }

    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaTabBinding {
        return FragmentAreaTabBinding.inflate(inflater, container, false)
    }
}