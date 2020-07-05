package com.sopt.ounce.login.ui

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.sopt.ounce.R
import com.sopt.ounce.util.StatusObject
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var imm : InputMethodManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //상태바 아이콘 색 변경
        StatusObject.setStatusBar(this)

        //키보드 리스너 설정
        observeKeyboard()

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        //EditText 화면 바깥 선택 시 키보드 숨기기
        layout_login_container.setOnClickListener {
            imm.hideSoftInputFromWindow(edt_login_id.windowToken,0)
        }


        edt_login_id.checkTextChangeListener { _, _ ->
            Toast.makeText(this,edt_login_id.text.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    // 키보드 안보일시 EditView 포커스 해제
    private fun observeKeyboard(){
        TedKeyboardObserver(this)
            .listen { isShow ->
                if( !isShow ){
                    // do checking EditText
                    edt_login_id.clearFocus()
                }
            }
    }


}
