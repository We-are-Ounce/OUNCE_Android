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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec

        val mode = MeasureSpec.getMode(heightMeasureSpec)

        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            var height = 0
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                child.measure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                val h = child.measuredHeight
                if (h > height) height = h
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}