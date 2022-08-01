package com.example.androidstudy_kotlin.view.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentTripDetailBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.TripDetailViewModel
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripDetailFragment : BaseFragment<FragmentTripDetailBinding>() {

    private val args: TripDetailFragmentArgs by navArgs()
    private val viewModel: TripDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tMapView = TMapView(context)

        binding.apply {
            viewModel.let {
                it.getTripDetailInfo(args.contentId, args.contentTypeId)

                // fragment에서는 owner를 this(activity에서 사용)가 아닌 viewLifecycleOwner를 사용
                it.tripDetail.observe(viewLifecycleOwner) { data ->
                    ivDetailImg.load(data.firstimage)
                    tvDetailTitle.text = data.title
                    tvDetailAddr.text = data.addr1
                    tvDetailContent.text = data.overview

                    tMapView.setSKTMapApiKey("l7xx76fbfbdb39464c419da151dc1d9b5bb9")
                    tMapView.setCenterPoint(data.mapx!!, data.mapy!!)
                    llDetailLocation.addView(tMapView)

                    val marker = TMapMarkerItem()
                    val point = TMapPoint(data.mapy, data.mapx)

                    val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.icon_marker)
                    marker.icon = Bitmap.createScaledBitmap(bitmap, 100, 100, false)

                    marker.setPosition(0.5f, 1.0f)
                    marker.tMapPoint = point
                    marker.name = "test"

                    tMapView.addMarkerItem("marker", marker)

                    // 길안내
                   btnFind.setOnClickListener {
                       val tmaptapi = TMapTapi(context)

                       if (tmaptapi.isTmapApplicationInstalled) {
                           tmaptapi.invokeRoute(data.addr1, data.mapx.toFloat(), data.mapy.toFloat())
                       } else {
                           Toast.makeText(context, "티맵 설치 X", Toast.LENGTH_SHORT).show()
                       }
                   }
                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTripDetailBinding {
        return FragmentTripDetailBinding.inflate(inflater, container, false)
    }
}