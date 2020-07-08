package com.sopt.ounce.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_main_appbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.main_search -> {
                    Log.d("ClickCallBack","search")
                }
            }
            true
        }

        bottom_main_appbar.setNavigationOnClickListener {
            Log.d("ClickCallBack","logo")
        }



    }


    fun methodManagerToFragment() : InputMethodManager {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return imm
    }
}