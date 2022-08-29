package com.example.androidstudy_kotlin.view.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.DialogProgressBinding
import com.example.androidstudy_kotlin.util.extension.runOnUiThread

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    protected lateinit var mActivity: Activity
    protected lateinit var mContext: Context

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        mContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mProgressDialog: AppCompatDialog by lazy {
        AppCompatDialog(context, R.style.AppcompatDialog_Transparent)
    }

    open fun showLoading() {
        try {
            val binding = DialogProgressBinding.inflate(LayoutInflater.from(context))
            mProgressDialog.setContentView(binding.root)
            //            Glide.with(this).load(R.raw.loading_icon).into(DrawableImageViewTarget(binding.ivFrameLoading))

            // UI 접근을 할 수 있음 -> 메인 스레드에서 동작
            runOnUiThread {
                if (!isDialogShowing(mProgressDialog) && !mActivity.isFinishing && !mActivity.isDestroyed) {
                    mProgressDialog.show()
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    open fun dismissLoading() {
        try {
            if (isDialogShowing(mProgressDialog)) {
                runOnUiThread { mProgressDialog.dismiss() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isDialogShowing(dialogInterface: DialogInterface?): Boolean {
        if (dialogInterface == null) {
            return false
        }
        if (dialogInterface is AlertDialog) {
            return dialogInterface.isShowing
        }
        return if (dialogInterface is Dialog) {
            dialogInterface.isShowing
        } else false
    }
}