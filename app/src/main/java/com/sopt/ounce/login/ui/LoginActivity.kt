@file:Suppress("DEPRECATION")

package com.sopt.ounce.login.ui


import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.amn.easysharedpreferences.EasySharedPreference
import com.amn.easysharedpreferences.EasySharedPreferenceConfig
import com.sopt.ounce.R
import com.sopt.ounce.catregister.CatRegisterActivity
import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.textCheckListener
import com.sopt.ounce.signup.ui.SignUpActivity
import com.sopt.ounce.util.StatusObject
import com.sopt.ounce.util.customEnqueue
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mImm: InputMethodManager
    private var mId: String = ""
    private var mPassword: String = ""

    private val mLoginRequest: OunceServiceImpl = OunceServiceImpl

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        EasySharedPreferenceConfig.initDefault(EasySharedPreferenceConfig.Builder()
            .inputFileName("easy_preference").inputMode(Context.MODE_PRIVATE).build())


        //상태바 아이콘 색 변경
        StatusObject.setStatusBar(this)


        //키보드 리스너 설정
        observeKeyboard()

        mImm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        //EditText 화면 바깥 선택 시 키보드 숨기기
        layout_login_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_login_id.windowToken, 0)
        }

        //기본적으로 로그인 버튼은 동작 불가
        //로그인 체크
        btn_login_btn.apply {
            setOnClickListener {
                checkIdPsw()
            }
            isEnabled = false
        }


        edt_login_password.apply {

            textCheckListener {
                mPassword = it.toString()
                checkButtonClick()
            }
            setOnFocusChangeListener { _, has ->
                if (has) {
                    edt_login_password.background.setColorFilter(
                        resources.getColor(R.color.black_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

        }

        edt_login_id.apply {
            textCheckListener {
                mId = it.toString()
                checkButtonClick()
            }
            setOnFocusChangeListener { _, has ->
                if (has) {
                    edt_login_id.background.setColorFilter(
                        resources.getColor(R.color.black_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }


        //회원가입 클릭 시 SignUpActivity 호출
        txt_login_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }

    private fun checkButtonClick() {
        if (mId.isNotBlank() && mPassword.isNotBlank()) {
            btn_login_btn.apply {
                isSelected = true
                isEnabled = true
            }
        } else {
            btn_login_btn.apply {
                isSelected = false
                isEnabled = false
            }
        }
    }

    // 키보드 안보일시 EditView 포커스 해제
    private fun observeKeyboard() {
        TedKeyboardObserver(this)
            .listen { isShow ->
                if (!isShow) {
                    // do checking EditText
                    edt_login_id.clearFocus()
                    edt_login_password.clearFocus()
                    edt_login_id.background.setColorFilter(
                        resources.getColor(R.color.white_three),
                        PorterDuff.Mode.SRC_IN
                    )
                    edt_login_password.background.setColorFilter(
                        resources.getColor(R.color.white_three),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
    }

    // 아이디와 패스워드 확인
    @Suppress("DEPRECATION")
    private fun checkIdPsw() {

        mLoginRequest.SERVICE.postSignIn(RequestLoginData(mId, mPassword))
            .customEnqueue(
                onSuccess = { it ->
                    it.data?.let { data ->

                        when (data.profileCount) {
                            0 -> {
                                EasySharedPreference.Companion.putString("accessToken",data.accessToken)
                                val intent = Intent(this, CatRegisterActivity::class.java)
                                startActivity(intent)
                            }
                            else -> {
                                EasySharedPreference.Companion.putInt("profileIdx",data.profileIdx)
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                },
                onError = {
                    //statusCode == 400
                    txt_login_fail.visibility = View.VISIBLE


                    edt_login_password.background.setColorFilter(
                        resources.getColor(R.color.pinkish_tan),
                        PorterDuff.Mode.SRC_IN
                    )

                    edt_login_id.background.setColorFilter(
                        resources.getColor(R.color.pinkish_tan),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            )


    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
    }

}

