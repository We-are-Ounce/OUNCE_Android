package com.sopt.ounce.login


import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textCheckListener(textCheck : (CharSequence?) -> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?,
                                       start: Int,
                                       count: Int,
                                       after: Int) = Unit

        override fun onTextChanged(s: CharSequence?,
                                   start: Int,
                                   before: Int,
                                   count: Int) {
            textCheck(s)
        }
    })
}
