package com.example.androidstudy_kotlin.view.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentPracticeBinding
import com.example.androidstudy_kotlin.view.adapter.ImagePagerAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.ui.custom.HorizontalMarginItemDecoration
import com.example.androidstudy_kotlin.view.viewmodel.TestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PracticeFragment : BaseFragment<FragmentPracticeBinding>() {

    private val viewModel: TestViewModel by viewModel()
    private val handler: Handler = Handler(Looper.getMainLooper())

    private var isFront = true
    private lateinit var flipFrontAnimatorSet: AnimatorSet
    private lateinit var flipBackAnimatorSet: AnimatorSet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // viewpager2 테스트
            vpImageTest.apply {
                offscreenPageLimit = 1

                val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
                val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
                val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

                // 양쪽 페이지 미리보기
                val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                    page.translationX = -pageTranslationX * position

                    // Next line scales the item's height. You can remove it if you don't want this effect
                    // 양쪽 페이지와의 길이 차이
//                    page.scaleY = 1 - (0.25f * Math.abs(position))
                    page.scaleY = 1 - (0.15f * Math.abs(position))

                    // If you want a fading effect uncomment the next line:
                    page.alpha = 0.25f + (1 - Math.abs(position))
                }
                setPageTransformer(pageTransformer)

                val itemDecoration = HorizontalMarginItemDecoration(requireContext(), R.dimen.viewpager_current_item_horizontal_margin)
                addItemDecoration(itemDecoration)

                // 자동 슬라이드
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        handler.removeCallbacks(runnable)
                        handler.postDelayed(runnable, 3000)
                    }
                })
            }

            viewModel.let {
                it.getAreaInfoTest(1, null)

                it.list.observe(viewLifecycleOwner) { data ->
                    val items = data.response.body.items.item

                    vpImageTest.adapter = ImagePagerAdapter(items)
                    vpImageTest.currentItem = Integer.MAX_VALUE / 2 - Math.ceil(items.size.toDouble() / 2).toInt() // setCurrentItem()도 가능
                }
            }

            // Flip Animation 적용
            val scale = requireContext().resources.displayMetrics.density
            cardFront.cameraDistance = 8000 * scale
            cardBack.cameraDistance = 8000 * scale

            flipFrontAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_front) as AnimatorSet
            flipBackAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_back) as AnimatorSet

            clFlip.setOnClickListener {
                isFront = if (isFront) {
                    flipCard(cardBack, cardFront)
                    false
                } else {
                    flipCard(cardFront, cardBack)
                    true
                }
            }
        }
    }

    // 뒤집기
    private fun flipCard(visibleView: View, inVisibleView: View) {
        flipFrontAnimatorSet.setTarget(inVisibleView)
        flipBackAnimatorSet.setTarget(visibleView)

        flipFrontAnimatorSet.start()
        flipBackAnimatorSet.start()
    }

    private val runnable = kotlinx.coroutines.Runnable {
        binding.vpImageTest.setCurrentItem(binding.vpImageTest.currentItem + 1, true)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPracticeBinding {
        return FragmentPracticeBinding.inflate(inflater, container, false)
    }
}