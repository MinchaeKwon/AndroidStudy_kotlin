package com.ediya.coupon.util

import android.util.Log
import com.example.androidstudy_kotlin.BuildConfig

/**
 * LogUtil
 */
object LogUtil {
    var isDebug = BuildConfig.DEBUG
    var LOG_TAG = "Android_kotlin::"

    fun d(str: String?) {
        if (!isDebug) {
            return
        }

        val log_tag = StringBuffer()
        val stackTrace = Exception().stackTrace

        log_tag.append(LOG_TAG)

        if (stackTrace.size > 1) {
            log_tag.append(stackTrace[1].fileName)
        }

        if (str != null) {
            Log.d(log_tag.toString(), str)
        }
    }

    fun e(str: String?) {
        if (!isDebug) return

        val log_tag = StringBuffer()
        val stackTrace = Exception().stackTrace

        log_tag.append(LOG_TAG)

        if (stackTrace.size > 1) {
            log_tag.append(stackTrace[1].fileName)
        }

        if (str != null) {

            //            var tempStr = str
            //            while (tempStr!!.length > 0) {
            //                if (tempStr.length > 4000) {
            //                    Log.e(log_tag.toString(), tempStr.substring(0, 4000))
            //                    tempStr = tempStr.substring(4000);
            //                } else {
            //                    Log.e(log_tag.toString(), tempStr)
            //                    break
            //                }
            //            }

            Log.e(log_tag.toString(), str)
        }
    }
}