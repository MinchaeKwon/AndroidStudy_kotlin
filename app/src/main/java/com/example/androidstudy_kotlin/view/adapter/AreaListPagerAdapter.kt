package com.example.androidstudy_kotlin.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidstudy_kotlin.view.ui.AreaListFragment

class AreaListPagerAdapter(
    private val areaCode: Int,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AreaListFragment.newInstance(areaCode, -1)
            1 -> AreaListFragment.newInstance(areaCode, 12)
            2 -> AreaListFragment.newInstance(areaCode, 14)
            3 -> AreaListFragment.newInstance(areaCode, 15)
            4 -> AreaListFragment.newInstance(areaCode, 28)
            5 -> AreaListFragment.newInstance(areaCode, 38)
            6 -> AreaListFragment.newInstance(areaCode, 39)
            else -> error("No Fragment")
        }
    }
}