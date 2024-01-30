package com.example.androidstudy_kotlin.view.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ediya.coupon.util.LogUtil
import com.example.androidstudy_kotlin.common.RESPONSE_RESULT_CODE
import com.example.androidstudy_kotlin.databinding.FragmentEdiyaMainBinding
import com.example.androidstudy_kotlin.network.ResponseState
import com.example.androidstudy_kotlin.view.base.BaseFragment
import com.example.androidstudy_kotlin.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EdiyaMainFragment : BaseFragment<FragmentEdiyaMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnPrev.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.let {
                it.getMain()

                it.mainResult.observe(viewLifecycleOwner) { res ->
                    when (res) {
                        is ResponseState.Loading -> {
//                            setLoading(true)
                        }

                        is ResponseState.Success -> {
                            val result = res.data!!.result
                            val data = res.data.data

                            if (result.code == RESPONSE_RESULT_CODE) {
                                LogUtil.d(data.toString())
//                                setLoading(false)
//                                setData(data)
                            } else {
//                                setLoading(true)
//                                showToast(result.message)
                            }
                        }

                        is ResponseState.Error -> {
//                            setLoading(true)

//                            res.errorData?.let { error -> //중복 로그인, 기간만료 시 토스트 숨김
//                                if (error.code != STATUS_CODE_409 && error.code != STATUS_CODE_510) {
//                                    showToast("${error.code}, ${error.message}")
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEdiyaMainBinding {
        return FragmentEdiyaMainBinding.inflate(inflater, container, false)
    }
}