package com.sopt.ounce.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.ounce.R

class OtherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val mFm = supportFragmentManager
        mFm.beginTransaction().add(R.id.layout_test,OtherUserFragment()).commit()
    }
}