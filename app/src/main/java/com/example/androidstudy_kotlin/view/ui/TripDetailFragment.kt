package com.example.androidstudy_kotlin.view.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentTripDetail2Binding
import com.example.androidstudy_kotlin.databinding.FragmentTripDetailBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.TripDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripDetailFragment : BaseFragment<FragmentTripDetail2Binding>() {

    private val args: TripDetailFragmentArgs by navArgs()
    private val viewModel: TripDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tMapView = TMapView(context)

        initAppbar()

        binding.apply {
            ivDetailHeaderBack.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.let {
                it.getTripDetailInfo(args.contentId, args.contentTypeId)

                // fragment에서는 owner를 this(activity에서 사용)가 아닌 viewLifecycleOwner를 사용
                it.tripDetail.observe(viewLifecycleOwner) { data ->
                    val item = data.response.body.items.item[0]

                    ivTripDetail.load(item.firstimage)
                    tvDetailHeaderTitle.text = item.title
                    tvTripDetailTitle.text = item.title
                    tvTripDetailAddr.text = item.addr1
                    tvTripDetailContent.text = item.overview

                    initMoreBtn()

                    // Tmap
                    tMapView.setSKTMapApiKey("l7xx76fbfbdb39464c419da151dc1d9b5bb9")
                    tMapView.setCenterPoint(item.mapx!!.toDouble(), item.mapy!!.toDouble())
                    llDetailLocation.addView(tMapView)

                    val marker = TMapMarkerItem()
                    val point = TMapPoint(item.mapy.toDouble(), item.mapx.toDouble())

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
                           tmaptapi.invokeRoute(item.addr1, item.mapx.toFloat(), item.mapy.toFloat())
                       } else {
                           Toast.makeText(context, "티맵 설치 X", Toast.LENGTH_SHORT).show()
                       }
                   }
                }

                it.isLoading.observe(viewLifecycleOwner) { isLoading ->
                    Log.e("minchae", "isLoading.observe : $isLoading")
                    if (isLoading) showLoading() else dismissLoading()
                }

                it.exception.observe(viewLifecycleOwner) { error ->
                    Log.e("minchae", "exception.observe error.code : ${error.code}")
                    Log.e("minchae", "exception.observe error.message : ${error.message}")
                }
            }
        }
    }

    // 더보기 버튼 설정
    private fun initMoreBtn() {
        // MainScope : UI 관련 작업을 처리하는 용도
        MainScope().launch {
            delay(300)
            try {
                val lineCount = binding.tvTripDetailContent.layout.lineCount

                if (lineCount > 0) {
                    if (binding.tvTripDetailContent.layout.getEllipsisCount(lineCount - 1) > 0) {
                        binding.tvTripDetailContentMore.isVisible = true
                    }

                    binding.tvTripDetailContentMore.setOnClickListener {
                        binding.tvTripDetailContent.maxLines = Int.MAX_VALUE
                        binding.tvTripDetailContentMore.isVisible = false
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initAppbar() {
        binding.detailAppbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                Log.d("aaa", " p1 = $p1")

                if (binding.detailAppbarLayout.totalScrollRange == 0 || p1 == 0) {
                    activity?.window?.statusBarColor = Color.argb(0, 0, 0, 0)
                    binding.detailHeader.setBackgroundColor(Color.argb(0, 0, 0, 0))

                    val white = Color.argb(255, 255, 255, 255)
                    binding.ivDetailHeaderBack.setColorFilter(white)

                    return
                }

                val ratio = p1.toFloat() / binding.detailAppbarLayout.totalScrollRange.toFloat()

                val rgb = (255 * Math.abs(ratio)).toInt()
                val iconRgb = Math.abs((255 * Math.abs(ratio)) - 255).toInt()

                activity?.window?.statusBarColor = Color.argb(rgb, 255, 255, 255)
                binding.detailHeader.setBackgroundColor(Color.argb(rgb, 255, 255, 255))

                val tintColor = Color.argb(iconRgb, iconRgb, iconRgb, iconRgb)
                binding.ivDetailHeaderBack.setColorFilter(tintColor)

                if (Math.abs(ratio) > 0.15) {
                    activity?.window?.insetsController?.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
//                    activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    activity?.window?.insetsController?.setSystemBarsAppearance(0, 0)
//                    activity?.window?.decorView?.systemUiVisibility = 0
                }

                binding.tvDetailHeaderTitle.isVisible = Math.abs(ratio) >= 1.0
            }

        })
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTripDetail2Binding {
        return FragmentTripDetail2Binding.inflate(inflater, container, false)
    }
}