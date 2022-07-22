package com.example.androidstudy_kotlin.view.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentAreaListBinding
import com.example.androidstudy_kotlin.databinding.FragmentAreaTabBinding
import com.example.androidstudy_kotlin.view.base.BaseFragment

class AreaListFragment: BaseFragment<FragmentAreaListBinding>() {

    companion object {

        const val AREA_NUMBER = "AREA_NUMBER"
        const val CATEGORY_NUMBER = "CATEGORY_NUMBER"

        fun newInstance(areaCode: Int, contentTypeId: Int): AreaListFragment {
            val bundle = Bundle()
            bundle.putInt(AREA_NUMBER, areaCode)
            bundle.putInt(CATEGORY_NUMBER, contentTypeId)
            val fragment = AreaListFragment()
//            fragment.arguments = bundle
            return fragment
        }
    }

    private var mFilterType: HomeListBaseFilter = HomeListBaseFilter.OPTION_02

//    lateinit var factory: HomeAreaListViewModel.HomeListViewModelFactory
//
//    private val viewModelArea: HomeAreaListViewModel by viewModels {
//        val areaCode = arguments?.getInt(AREA_NUMBER)
//        val contentTypeId = arguments?.getInt(CATEGORY_NUMBER)
//        HomeAreaListViewModel.provideFactory(
//            factory,
//            areaCode ?: -1,
//            if (contentTypeId == -1) null else contentTypeId
//        )
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
//        binding.viewModel = viewModelArea
//        binding.tvHomeListFilter.text = mFilterType.options
//
//        val homeListPagingAdapter = HomeAreaListPagingAdapter()
//        binding.rvHomeList.apply {
//            adapter = homeListPagingAdapter
//            setHasFixedSize(true)
//            addItemDecoration(HomeListDecoration(20.dpToPx()))
//        }
//        lifecycleScope.launchWhenCreated {
//            viewModelArea.listData.collectLatest {
//                homeListPagingAdapter.submitData(it)
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            homeListPagingAdapter.loadStateFlow.collectLatest { loadStates ->
//                binding.tvHomeListCount.text = "총 ${homeListPagingAdapter.itemCount}"
//            }
//        }
//
//        binding.llHomeListFilter.setOnClickListener {
//
//            rotateFilterArrow(false, binding.ivHomeListFilterDropdown)
//
//            val data = FilterListData("정렬 방법", HomeListBaseFilter::class.java, mFilterType)
//            val dialog = FilterBottomDialog.newInstance(data) {
//                if (it == "dismiss") {
//                    rotateFilterArrow(true, binding.ivHomeListFilterDropdown)
//                }else {
//                    mFilterType = HomeListBaseFilter.valueOf(it)
//                    binding.tvHomeListFilter.text = mFilterType.options
//                    viewModelArea.setArrage(mFilterType.optionValue)
//                    homeListPagingAdapter.refresh()
//                }
//            }
//            dialog.show(childFragmentManager, dialog.tag)
//        }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            homeListPagingAdapter.loadStateFlow.collectLatest { loadStates ->
//                Log.d("error addLoadStateListener", "${loadStates}")
//                binding.llHomeListError.isVisible = loadStates.source.refresh is LoadState.Error
//                viewModelArea._isLoading.value = loadStates.source.append is LoadState.Loading
//                val errorState = loadStates.source.refresh as? LoadState.Error
//                    ?: loadStates.refresh as? LoadState.Error
//                    ?: loadStates.append as? LoadState.Error
//                    ?: loadStates.prepend as? LoadState.Error
//                errorState?.let {
//                    if (!it.error.message.isNullOrEmpty()) {
//                        viewModelArea._toastMessage.emit(it.error.message!!)
//                    }
//                }
//
//            }
//        }
//
//        binding.btnHomeListRetry.setOnClickListener {
//            homeListPagingAdapter.retry()
//        }

        return root
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