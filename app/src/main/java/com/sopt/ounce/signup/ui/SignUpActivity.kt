package com.sopt.ounce.signup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.signup.adapter.SignUpPagerAdapter
import com.sopt.ounce.signup.data.UserInfoObject
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mViewpagerAdapter : SignUpPagerAdapter
    private var mCheck : Boolean = false
    private var mFinal : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //상태바 아이콘 변경
        StatusObject.setStatusBar(this)

        //뷰페이저 init
        initPager()

        //확인 버튼 누를 시 다음 페이지 이동
        //기능 구현 후 조건 걸어서 클릭 못하게 하는거 필요
        btn_signup_ok.setOnClickListener {
            if(vp_signup.currentItem < vp_signup.childCount) {
                vp_signup.currentItem += 1
                buttonEnable(false)
            }
            else{
                if (UserInfoObject.finish){
                    val intent = Intent(this, SignUpFinishActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
        //좌측 상단 뒤로가기 버튼 누를 시 뒤로가기
        img_signup_back.setOnClickListener {
            if(vp_signup.currentItem != 0){
                vp_signup.currentItem -= 1
                buttonEnable(false)
            }
            else{
                finish()
            }
        }

        //취소 버튼 누르면 이전 화면으로 돌아가기
        btn_signup_cancle.setOnClickListener {
            finish()
        }

    }

    private fun initPager(){

        mViewpagerAdapter = SignUpPagerAdapter(supportFragmentManager)
        vp_signup.adapter = mViewpagerAdapter

        dot_signup_indicator.setViewPager(vp_signup)
        dot_signup_indicator.dotsClickable = false

    }

    fun methodManagerToFragment() : InputMethodManager {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return imm
    }

    //확인 버튼 클릭 활성화
    @Suppress("DEPRECATION")
    fun buttonEnable(enable : Boolean){
        if(enable) {
            btn_signup_ok.apply {
                isEnabled = true
                setTextColor(resources.getColor(R.color.white))
            }
        }
        else{
            btn_signup_ok.apply {
                isEnabled = false
                setTextColor(resources.getColor(R.color.greyish_two))
            }
        }

    }



}