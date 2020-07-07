package com.sopt.ounce.util

import android.app.Activity
import android.content.Context
import android.view.View

object StatusObject {
    @Suppress("DEPRECATION")
    fun setStatusBar(activity: Activity){
        activity.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}