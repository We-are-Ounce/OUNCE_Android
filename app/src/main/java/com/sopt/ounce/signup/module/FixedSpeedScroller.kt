package com.sopt.ounce.signup.module

import android.content.Context

import android.widget.Scroller

class FixedSpeedScroller :Scroller{
    private val mDuration = 600

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, interpolator : android.view.animation.Interpolator) :
            super(context!!,interpolator)

    constructor(context : Context?,
                interpolator : android.view.animation.Interpolator,
                b : Boolean)  : super(context!!, interpolator, b)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy,mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}