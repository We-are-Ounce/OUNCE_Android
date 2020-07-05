package com.sopt.ounce.login

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun EditText.textCheckListener(textCheck : (Editable?) -> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            textCheck(s)
        }

        override fun beforeTextChanged(s: CharSequence?,
                                       start: Int,
                                       count: Int,
                                       after: Int) = Unit

        override fun onTextChanged(s: CharSequence?,
                                   start: Int,
                                   before: Int,
                                   count: Int) = Unit
    })
}

fun EditText.checkTextChangeListener(hide : (View?,Boolean?) -> Unit){
    this.setOnFocusChangeListener(object : View.OnFocusChangeListener{
        override fun onFocusChange(p0: View?, p1: Boolean) {
            if(!p1){
                hide(p0,p1)
            }
        }
    })
}
