package com.sopt.ounce.configuration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.login.ui.LoginActivity
import com.sopt.ounce.util.StatusObject
import com.sopt.ounce.util.showToast
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        StatusObject.setStatusBar(this)

        img_configu_back.setOnClickListener {
            finish()
        }

        txt_configu_logout.setOnClickListener {
            EasySharedPreference.Companion.putString("accessToken","")
            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
        }

        txt_configu_info.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

        txt_configu_terms.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

        txt_configu_version.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

        txt_configu_faq.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

        txt_configu_report.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

        txt_configu_delete.setOnClickListener {
            this.showToast("아직 준비 중입니다.")
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}