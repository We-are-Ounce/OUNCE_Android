package com.sopt.ounce.signup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentManager
import com.sopt.ounce.R
import com.sopt.ounce.signup.adapter.SignUpPagerAdapter
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mViewpagerAdapter : SignUpPagerAdapter
    private var mCheck : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //뷰페이저 init
        initPager()

        //좌측 상단 뒤로가기 버튼 누를시 뒤로가기
        btn_signup_ok.setOnClickListener {
            vp_signup.currentItem += 1
        }

        img_signup_back.setOnClickListener {
            if(vp_signup.currentItem != 0){
                vp_signup.currentItem -= 1
            }
            else{
                finish()
            }
        }

        btn_signup_cancle.setOnClickListener {
            finish()
        }

    }

    private fun initPager(){

        mViewpagerAdapter = SignUpPagerAdapter(supportFragmentManager)
        vp_signup.adapter = mViewpagerAdapter

        dot_indicator.setViewPager(vp_signup)
        dot_indicator.dotsClickable = false

    }
}