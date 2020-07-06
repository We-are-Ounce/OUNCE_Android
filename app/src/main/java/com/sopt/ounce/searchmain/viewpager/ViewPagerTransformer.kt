package com.sopt.ounce.searchmain.viewpager

import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class ViewPagerTransformer : ViewPager.PageTransformer{
    override fun transformPage(page: View, position: Float) {
        val r = 1- abs(position)
        page.scaleY = (0.85f + r * 0.2f)
    }
}