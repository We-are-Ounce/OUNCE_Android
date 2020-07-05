package com.sopt.ounce.login.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.sopt.ounce.R
import com.sopt.ounce.login.checkTextChangeListener
import com.sopt.ounce.util.StatusObject
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mImm : InputMethodManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //상태바 아이콘 색 변경
        StatusObject.setStatusBar(this)

        //키보드 리스너 설정
        observeKeyboard()

        mImm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        //EditText 화면 바깥 선택 시 키보드 숨기기
        layout_login_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_login_id.windowToken,0)
        }

        btn_login_btn.setOnClickListener {
            checkIdPsw()
        }

    }

    // 키보드 안보일시 EditView 포커스 해제
    private fun observeKeyboard(){
        TedKeyboardObserver(this)
            .listen { isShow ->
                if( !isShow ){
                    // do checking EditText
                    edt_login_id.clearFocus()
                    edt_login_password.clearFocus()
                }
            }
    }

    @Suppress("DEPRECATION")
    private fun checkIdPsw(){
        val id = edt_login_id.text.toString()
        val password = edt_login_password.text.toString()
        var visible = true
        if (id.isEmpty() || password.isEmpty()){

            if (visible){
                txt_login_fail.visibility = View.VISIBLE
                visible = false
            }

            edt_login_password.background.setColorFilter(resources.getColor(R.color.pinkish_tan),
            PorterDuff.Mode.SRC_IN)

            edt_login_id.background.setColorFilter(resources.getColor(R.color.pinkish_tan),
            PorterDuff.Mode.SRC_IN)

        }
    }


}
