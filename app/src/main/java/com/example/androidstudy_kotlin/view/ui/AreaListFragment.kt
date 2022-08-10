package com.example.androidstudy_kotlin.view.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.androidstudy_kotlin.view.adapter.RecyclerLoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.enum.Sorting
import com.example.androidstudy_kotlin.databinding.FragmentAreaListBinding
import com.example.androidstudy_kotlin.util.extension.dpToPx
import com.example.androidstudy_kotlin.view.adapter.AreaListInfoPagingAdapter
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.AreaViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

class AreaListFragment : BaseFragment<FragmentAreaListBinding>() {

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

    private var mFilterType: Sorting = Sorting.OPTION_02
    private val infoAdapter = AreaListInfoPagingAdapter()

    private val viewModel: AreaViewModel by viewModel {
        val areaCode = arguments?.getInt(AREA_NUMBER)
        val contentTypeId = arguments?.getInt(CATEGORY_NUMBER)

        parametersOf(areaCode?: -1, if (contentTypeId == -1) null else contentTypeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // recyclerview 설정
            rvAreaList.apply {
                setHasFixedSize(true)
                addItemDecoration(ListDecoration(20.dpToPx()))

                layoutManager = LinearLayoutManager(context)
                adapter = infoAdapter.withLoadStateHeaderAndFooter(RecyclerLoadStateAdapter { infoAdapter.retry() }, RecyclerLoadStateAdapter { infoAdapter.retry() })
            }

            infoAdapter.addLoadStateListener {
                if (it.refresh is LoadState.Error) {
                    infoAdapter.retry()
                }

                if (it.prepend.endOfPaginationReached) {
                    dismissLoading()
                }
            }

            viewModel.let {
                it.getAreaInfoPaging2()
                it.list.observe(viewLifecycleOwner) { data ->
                    infoAdapter.submitData(lifecycle, data)
                }

                // lifecycleScope : activity, fragment에서 사용
                lifecycleScope.launchWhenStarted {
                    showLoading()

                    it.getAreaInfoPaging().collect { data ->
                        infoAdapter.submitData(data)
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

            viewLifecycleOwner.lifecycleScope.launch {
                infoAdapter.loadStateFlow.collectLatest { loadStates ->
                    tvAreaListCount.text = "총 ${infoAdapter.itemCount}"
                }
            }

            // 정렬 스피너
            spSort.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.sorting))
            spSort.setSelection(1, false)

            spSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    mFilterType = Sorting.from(spSort.selectedItem.toString())
                    viewModel.setArrange(mFilterType.optionValue)

                    infoAdapter.refresh()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaListBinding {
        return FragmentAreaListBinding.inflate(inflater, container, false)
    }
}

class ListDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = space
    }

}