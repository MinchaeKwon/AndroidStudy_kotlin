package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidstudy_kotlin.databinding.FragmentTmapBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.skt.Tmap.TMapView

class TmapFragment : BaseFragment<FragmentTmapBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tMapView = TMapView(context)

        binding.apply {
            tMapView.setSKTMapApiKey("l7xx76fbfbdb39464c419da151dc1d9b5bb9")
//            tMapView.setIconVisibility(true)
            llTmap.addView(tMapView)
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTmapBinding {
        return FragmentTmapBinding.inflate(inflater, container, false)
    }
}