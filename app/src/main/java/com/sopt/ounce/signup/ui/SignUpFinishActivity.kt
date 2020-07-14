package com.sopt.ounce.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.ounce.R
import com.sopt.ounce.catregister.CatRegisterActivity
import com.sopt.ounce.login.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

class SignUpFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_finish)

        btn_go_register.setOnClickListener {
            val intent = Intent(this,CatRegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SingUpFinishi","Call onDestroy()")
    }
}