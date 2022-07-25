package com.example.androidstudy_kotlin.view.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.androidstudy_kotlin.view.adapter.RecyclerLoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.databinding.FragmentAreaListBinding
import com.example.androidstudy_kotlin.view.adapter.AreaListInfoPagingAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel

class AreaListFragment: BaseFragment<FragmentAreaListBinding>() {

    companion object {
        const val AREA_NUMBER = "AREA_NUMBER"
        const val CATEGORY_NUMBER = "CATEGORY_NUMBER"

        fun newInstance(areaCode: Int, contentTypeId: Int): AreaListFragment {
            val bundle = Bundle()
            bundle.putInt(AREA_NUMBER, areaCode)
            bundle.putInt(CATEGORY_NUMBER, contentTypeId)

            val fragment = AreaListFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    private var mFilterType: HomeListBaseFilter = HomeListBaseFilter.OPTION_02
    private val infoAdapter = AreaListInfoPagingAdapter()

    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvAreaList.setHasFixedSize(true)
            rvAreaList.layoutManager = LinearLayoutManager(context)
            rvAreaList.adapter = infoAdapter.withLoadStateHeaderAndFooter(RecyclerLoadStateAdapter { infoAdapter.retry() }, RecyclerLoadStateAdapter { infoAdapter.retry() })

            infoAdapter.addLoadStateListener {
                Log.e("minchae", "pre ${it.prepend}")
                Log.e("minchae", "ap ${it.append}")
                Log.e("minchae", "re ${it.refresh}")

                if (it.refresh is LoadState.Error) {
                    infoAdapter.retry()
                }

                if (it.prepend.endOfPaginationReached) {
                    dismissLoading()
                }
            }

            val areaCode = arguments?.getInt(AREA_NUMBER)
            var contentTypeId = arguments?.getInt(CATEGORY_NUMBER)

            viewModel.let { it ->
                lifecycleScope.launchWhenStarted {
                    Log.e("minchae", "launchWhenStarted")
                    showLoading()

                    if (areaCode != null) {
                        contentTypeId = if (contentTypeId == -1) null else contentTypeId

                        it.getAreaInfoPaging(areaCode, contentTypeId).collect { data ->
                            infoAdapter.submitData(data)
                        }
                    }
                }

                it.isLoading.observe(viewLifecycleOwner) { isLoading ->
                    Log.e("minchae", "isLoading.observe : $isLoading")
                    if (isLoading) showLoading() else dismissLoading()
                }

                it.exception.observe(viewLifecycleOwner) { code ->
                    Log.e("minchae", "exception.observe : $code")
                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaListBinding {
        return FragmentAreaListBinding.inflate(inflater, container, false)
    }
}

enum class HomeListBaseFilter(val options: String, val optionValue: String) {
    OPTION_01("제목순", "O"),
    OPTION_02("조회순", "P"),
    OPTION_03("수정일순", "Q"),
    OPTION_04("생성일순", "R");

    override fun toString(): String {
        return options.toString()
    }
    companion object {
        fun from(type: String?): HomeListBaseFilter = HomeListBaseFilter.values().find { it.options == type } ?: OPTION_02
    }
}

class HomeListDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = space
    }

}