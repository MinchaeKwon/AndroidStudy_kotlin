package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.enum.Area
import com.example.androidstudy_kotlin.model.FilterListData
import com.example.androidstudy_kotlin.model.Region
import com.example.androidstudy_kotlin.databinding.FragmentAreaTabBinding
import com.example.androidstudy_kotlin.util.extension.rotateFilterArrow
import com.example.androidstudy_kotlin.view.adapter.AreaListPagerAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.dialog.FilterBottomDialog
import com.google.android.material.tabs.TabLayoutMediator

class AreaTabFragment : BaseFragment<FragmentAreaTabBinding>() {
    private val args: AreaTabFragmentArgs by navArgs()
    private var mSelectedType: Area = Area.OPTION_01

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabTitle = resources.getStringArray(R.array.tab_title)

        binding.apply {
            mSelectedType = Area.from(args.region?.areaName ?: "서울")
            tvSelectArea.text = mSelectedType.areaName

            // viewpager2 사용
            vp2Tab.adapter = AreaListPagerAdapter(args.region?.areaCode?.toInt() ?: 1, childFragmentManager, lifecycle)

            // 탭 추가
            TabLayoutMediator(tlTab, vp2Tab) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()

            // 지역 선택 클릭시 바텀시트 띄우기
            llSelectArea.setOnClickListener {
                rotateFilterArrow(false, ivSelectArea)

                val data = FilterListData("지역 선택", Area::class.java, mSelectedType)
                val dialog = FilterBottomDialog.newInstance(data) {
                    if (it == "dismiss") {
                        rotateFilterArrow(true, ivSelectArea)
                    } else {
                        mSelectedType = Area.valueOf(it)
                        tvSelectArea.text = mSelectedType.areaName

                        val action = AreaTabFragmentDirections.actionAreaTabFragmentSelf(Region(mSelectedType.areaCode.toString(), mSelectedType.areaName))
//                        action.region = Region(mSelectedType.areaCode.toString(), mSelectedType.areaName) // 에러 발생
                        findNavController().navigate(action)
                    }
                }

                dialog.show(childFragmentManager, dialog.tag)
            }
        }

    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaTabBinding {
        return FragmentAreaTabBinding.inflate(inflater, container, false)
    }
}