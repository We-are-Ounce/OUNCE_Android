@file:Suppress("DEPRECATION")

package com.sopt.ounce.catregister.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sopt.ounce.R
import com.sopt.ounce.login.ui.LoginActivity
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_cat_register_finish.*
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

class CatRegisterFinishActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_register_finish)

        StatusObject.setStatusBar(this)



        btn_go_main.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val thread = GifThread()
        thread.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    inner class GifThread : Thread(){
        override fun run() {

            runOnUiThread {

                handler.postDelayed({
                    Glide.with(this@CatRegisterFinishActivity)
                        .asGif()
                        .load(R.raw.catprofile_finish)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(ani_cat_finish)

                },500)

            }

        }
    }

}