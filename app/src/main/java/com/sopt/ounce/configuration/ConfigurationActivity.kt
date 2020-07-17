package com.sopt.ounce.configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.ounce.R
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        StatusObject.setStatusBar(this)

    }
}