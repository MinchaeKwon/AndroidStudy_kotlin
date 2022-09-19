package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.androidstudy_kotlin.PracticeApplication
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.common.AutoScrollHorizontalListLayoutManager
import com.example.androidstudy_kotlin.databinding.FragmentRecyclerBinding
import com.example.androidstudy_kotlin.view.adapter.PicsumAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.TestViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DELAY_BETWEEN_SCROLL_MS = 25L
private const val SCROLL_DX = 10
private const val DIRECTION_RIGHT = 1

class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>() {

    private val viewModel: TestViewModel by viewModel()
    private val picsumAdapter = PicsumAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvPicsum.apply {
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = AutoScrollHorizontalListLayoutManager(context)
                adapter = picsumAdapter
            }

            viewModel.let {
                it.getPictureList()

                it.picList.observe(viewLifecycleOwner) { res ->
                    picsumAdapter.apply {
                        differ.submitList(res)
                    }

                    lifecycleScope.launch {
//                        autoScrollFeaturesList()
                        autoScroll()
                    }
                }
            }
        }
    }

    private tailrec suspend fun autoScrollFeaturesList() {
        if (binding.rvPicsum.canScrollHorizontally(DIRECTION_RIGHT)) {
            binding.rvPicsum.smoothScrollBy(SCROLL_DX, 0)
        } else {
            val firstPosition = (binding.rvPicsum.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (firstPosition != RecyclerView.NO_POSITION) {
                val currentList = picsumAdapter.differ.currentList
                val secondPart = currentList.subList(0, firstPosition)
                val firstPart = currentList.subList(firstPosition, currentList.size)

                picsumAdapter.differ.submitList(firstPart + secondPart)
            }
        }

        delay(DELAY_BETWEEN_SCROLL_MS)
        autoScrollFeaturesList()
    }

    val scrollHandler = Handler(Looper.getMainLooper())
    private fun autoScroll() {
        try {
            var delaySpeed = 0L
            var delaySpeed2 = 0L
            val runnable: Runnable = object : Runnable {
                var count = 0
                override  fun run() {
                    val size = picsumAdapter.itemCount

                    val firstPosition = (binding.rvPicsum.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val lastPosition = (binding.rvPicsum.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (lastPosition > count) { // 뒤로 이동시 포지션 변경처리
                        count = lastPosition
                    }

                    if (count > 1 && (count - firstPosition) > 5) { // 앞으로 이동시 포지션 변경처리
                        count = lastPosition
                    }

                    if (count == size) {    // 마지막 위치시 초기화 처리
                        count = 0
                        binding.rvPicsum.scrollToPosition(count)

                        autoScroll()
                    } else if (count < size) {
                        binding.rvPicsum.smoothScrollToPosition(++count)
                    }
                    scrollHandler.postDelayed(this, delaySpeed2)

                    delaySpeed = 1000L
                    delaySpeed2 = 2003L
                }
            }
            scrollHandler.postDelayed(runnable, delaySpeed)
        } catch (e: Exception) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        scrollHandler.removeCallbacksAndMessages(null)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRecyclerBinding {
        return FragmentRecyclerBinding.inflate(inflater, container, false)
    }
}