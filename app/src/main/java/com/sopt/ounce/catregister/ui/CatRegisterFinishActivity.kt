package com.sopt.ounce.catregister.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.ounce.R
import com.sopt.ounce.login.ui.LoginActivity
import com.sopt.ounce.main.ui.MainActivity
import kotlinx.android.synthetic.main.activity_cat_register_finish.*

class CatRegisterFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_register_finish)

        btn_go_main.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}