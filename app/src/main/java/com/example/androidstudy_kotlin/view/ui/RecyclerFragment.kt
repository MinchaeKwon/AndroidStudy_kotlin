package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.R
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
                adapter = picsumAdapter
                scheduleLayoutAnimation() // 애니메이션 적용
            }

            viewModel.let {
                it.getPictureList()

                it.picList.observe(viewLifecycleOwner) { res ->
                    picsumAdapter.apply {
                        differ.submitList(res)
                    }
                }
            }

            lifecycleScope.launch {
                autoScrollFeaturesList()
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

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRecyclerBinding {
        return FragmentRecyclerBinding.inflate(inflater, container, false)
    }
}