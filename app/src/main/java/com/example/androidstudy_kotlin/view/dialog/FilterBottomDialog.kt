package com.example.androidstudy_kotlin.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.androidstudy_kotlin.data.model.FilterListData
import com.example.androidstudy_kotlin.databinding.DialogFilterBottomBinding
import com.example.androidstudy_kotlin.util.extension.dpToPx
import com.example.androidstudy_kotlin.view.ui.custom.FilterTextItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomDialog: BottomSheetDialogFragment() {

    companion object {
        const val KEY_FilterListData = "KEY_FilterListData"
        lateinit var clickResult: (String) -> Unit // String은 파라미터 타입, Unit은 반환 타입

        fun <T: Enum<T>> newInstance(data: FilterListData<T>, callback: (String) -> Unit): FilterBottomDialog {
            val fragment = FilterBottomDialog()
            val bundle = Bundle()

            bundle.putParcelable(KEY_FilterListData, data)
            fragment.arguments = bundle
            clickResult = callback

            return fragment
        }
    }

    private lateinit var binding: DialogFilterBottomBinding

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        clickResult.invoke("dismiss")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DialogFilterBottomBinding.inflate(inflater, container, false)
        binding = DialogFilterBottomBinding.inflate(layoutInflater)

        val data = arguments?.getParcelable<FilterListData<*>>(KEY_FilterListData)
        binding.tvFilterDialogTitle.text = data?.title

        data?.option?.enumConstants?.forEach { enum ->
            val item = FilterTextItem(binding.root.context)

            item.setItem(enum.toString(), enum == data.isSelected)
            item.setOnClickListener {
                clickResult.invoke(enum.name)
                dismissAllowingStateLoss()
            }

            binding.llFilterDialog.addView(item)
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface: DialogInterface? ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog?
            setupRatio(bottomSheetDialog!!)
        }

        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?

        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet!!)
        val layoutParams = bottomSheet!!.layoutParams
        val data = arguments?.getParcelable<FilterListData<*>>(KEY_FilterListData)
        var size = (data?.option?.enumConstants?.size ?: 4)

        val defaultH = if (size > 4) {
            size = 4
            72.dpToPx() + 26.dpToPx()
        } else {
            72.dpToPx()
        }

        layoutParams.height = defaultH + (52.dpToPx() * size)  // getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

}