package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.androidstudy_kotlin.databinding.FragmentTripDetailBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.TripDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripDetailFragment : BaseFragment<FragmentTripDetailBinding>() {

    private val args: TripDetailFragmentArgs by navArgs()
    private val viewModel: TripDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel.let {
                it.getTripDetailInfo(args.contentId, args.contentTypeId)

                it.list.observe(viewLifecycleOwner) { data ->
                    ivDetailImg.load(data.firstimage)
                    tvDetailTitle.text = data.title
                    tvDetailAddr.text = data.addr1
                    tvDetailContent.text = data.overview
                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTripDetailBinding {
        return FragmentTripDetailBinding.inflate(inflater, container, false)
    }
}