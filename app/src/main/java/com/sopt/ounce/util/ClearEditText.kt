package com.sopt.ounce.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.sopt.ounce.R

class ClearEditText :
    AppCompatEditText,
    TextWatcher,
    View.OnTouchListener,
    View.OnFocusChangeListener {

    private var clearDrawable: Drawable? = null
    private var onFocus: OnFocusChangeListener? = null
    private var onTouch: OnTouchListener? = null

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet) : super(context!!, attrs)


    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int)
            : super(context!!, attrs, defStyleAttr)

    init {
        val temDrawable = ContextCompat.getDrawable(context, R.drawable.ic_expand_close)
        clearDrawable = temDrawable?.let { DrawableCompat.wrap(it) }
        clearDrawable?.let { DrawableCompat.setTintList(it, hintTextColors) }
        clearDrawable?.setBounds(0,
            0,
            clearDrawable!!.intrinsicWidth,
            clearDrawable!!.intrinsicHeight
        )

        setClearIconVisible(false)

        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)


    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(hasFocus){
            setClearIconVisible(text?.length!! > 0)
        }else{
            setClearIconVisible(false)
        }

        if(onFocus != null){
            onFocus?.onFocusChange(view, hasFocus)
        }
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        val x = motionEvent?.x?.toInt()

        if(clearDrawable!!.isVisible && x!! > width - paddingRight - clearDrawable!!.intrinsicWidth){
            if(motionEvent.action == MotionEvent.ACTION_UP){
                text = null
            }

            return true
        }

        if (onTouch != null){
            return onTouch!!.onTouch(view,motionEvent)
        }else{
            return false
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if(isFocused){
            setClearIconVisible(text!!.isNotEmpty())
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {}

    private fun setClearIconVisible(visible : Boolean){
        clearDrawable?.setVisible(visible,false)
        setCompoundDrawables(null, null, if (visible) clearDrawable else null, null)
    }
}