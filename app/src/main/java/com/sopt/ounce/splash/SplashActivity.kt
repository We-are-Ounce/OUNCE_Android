@file:Suppress("DEPRECATION")

package com.sopt.ounce.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.sopt.ounce.R
import com.sopt.ounce.login.ui.LoginActivity

class SplashActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val thread = Thread2()
        thread.start()
    }

    inner class Thread2() : Thread(){
        override fun run() {
            runOnUiThread {
                handler.postDelayed({
                    overridePendingTransition(R.anim.splash_start,R.anim.splash_end)
                    startActivity(Intent(application,LoginActivity::class.java))
                    finish()
                },3000)
            }
        }
    }

}