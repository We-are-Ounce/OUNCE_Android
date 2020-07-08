package com.sopt.ounce.searchmain

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


    }
    fun setImmToFragment() : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
}