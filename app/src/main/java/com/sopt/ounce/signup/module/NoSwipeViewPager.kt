package com.sopt.ounce.signup.module

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import androidx.viewpager.widget.ViewPager

class NoSwipeViewPager : ViewPager{
    var enable = false
    private var mScroller: FixedSpeedScroller? = null

    constructor(context : Context?) : super(context!!){}
    constructor(context: Context?, attrs : AttributeSet?) : super(context!!, attrs){}

    init {
        try {
            val viewPager = ViewPager::class.java
            val scroller = viewPager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            mScroller = FixedSpeedScroller(context, DecelerateInterpolator())
            scroller.set(this, mScroller)
        }
        catch (ignored : Exception){}
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(enable) return super.onInterceptTouchEvent(ev)
        else{
            when {
                ev?.action == MotionEvent.ACTION_MOVE -> {}
                super.onInterceptTouchEvent(ev) -> super.onTouchEvent(ev)
            }
        }

        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (enable) super.onTouchEvent(ev)
        else ev?.action != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev)

    }


}