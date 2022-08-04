package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.model.Region
import com.example.androidstudy_kotlin.databinding.FragmentMainBinding
import com.example.androidstudy_kotlin.view.adapter.ImagePagerAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.TestViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Math.ceil

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: TestViewModel by viewModel()
    private lateinit var job: Job

    private val handler: Handler = Handler(Looper.getMainLooper())

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
            }

            btnTmap.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_tmapFragment)
            }

            btnMotion.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_motionFragment)
            }

            // viewpager2 테스트
            viewModel.let {
                it.getAreaInfoTest(1, null)

                it.list.observe(viewLifecycleOwner) { data ->
                    val items = data.response.body.items.item

                    vpImageTest.adapter = ImagePagerAdapter(items)
                    vpImageTest.setCurrentItem(Integer.MAX_VALUE / 2 - ceil(items.size.toDouble() / 2).toInt())
                }
            }

            // 이미지 자동 슬라이드
            vpImageTest.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    handler.removeCallbacks(runnable)
                    handler.postDelayed(runnable, 3000)
                }
            })
        }
    }

    private val runnable = Runnable {
        binding.vpImageTest.setCurrentItem(binding.vpImageTest.currentItem + 1, true)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}