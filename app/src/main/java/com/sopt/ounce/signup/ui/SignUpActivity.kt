package com.sopt.ounce.signup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.server.UserServiceImpl
import com.sopt.ounce.signup.adapter.SignUpPagerAdapter
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.UserInfoObject
import com.sopt.ounce.util.StatusObject
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mViewpagerAdapter: SignUpPagerAdapter
    private val mUserService: UserServiceImpl = UserServiceImpl

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
            if (vp_signup.currentItem < vp_signup.childCount - 1) {
                vp_signup.currentItem += 1
                buttonEnable(false)
            } else {

                "UserInfoCheck".showLog("${UserInfoObject.email}")
                "UserInfoCheck".showLog("${UserInfoObject.id}")
                "UserInfoCheck".showLog("${UserInfoObject.password}")

                mUserService.service.postSignUp(
                    RequestSignUpdata(
                        email = UserInfoObject.email,
                        id = UserInfoObject.id,
                        password = UserInfoObject.password
                    )
                ).customEnqueue(
                    onSuccess = {
                        it.data?.let { data ->
                            EasySharedPreference.Companion.putString(
                                "accessToken",
                                data.accessToken
                            )
                            val intent = Intent(this, SignUpFinishActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    },
                    onError = {
                        //status == 400
                        Toast.makeText(this, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                )


            }

        }
        //좌측 상단 뒤로가기 버튼 누를 시 뒤로가기
        img_signup_back.setOnClickListener {
            if (vp_signup.currentItem != 0) {
                vp_signup.currentItem -= 1
            } else {
                finish()
            }
        }

        //취소 버튼 누르면 이전 화면으로 돌아가기
        btn_signup_cancle.setOnClickListener {
            finish()
        }

    }

    private fun initPager() {

        mViewpagerAdapter = SignUpPagerAdapter(supportFragmentManager)
        vp_signup.adapter = mViewpagerAdapter

        dot_signup_indicator.setViewPager(vp_signup)
        dot_signup_indicator.dotsClickable = false

    }

    fun methodManagerToFragment(): InputMethodManager {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return imm
    }

    //확인 버튼 클릭 활성화
    @Suppress("DEPRECATION")
    fun buttonEnable(enable: Boolean) {
        if (enable) {
            btn_signup_ok.apply {
                isEnabled = true
                setTextColor(resources.getColor(R.color.white))
            }
        } else {
            btn_signup_ok.apply {
                isEnabled = false
                setTextColor(resources.getColor(R.color.greyish_two))
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        UserInfoObject.email=""
        UserInfoObject.id = ""
        UserInfoObject.password = ""
    }


}