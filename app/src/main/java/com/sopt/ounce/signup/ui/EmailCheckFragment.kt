@file:Suppress("DEPRECATION")

package com.sopt.ounce.signup.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_email_check.view.*


class EmailCheckFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v : View
    private lateinit var mImm : InputMethodManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_email_check, container, false)
        observeKeyboard()

        //이메일에 인증코드 전송 버튼 리스너
        v.btn_email_send.setOnClickListener {
            checkEmail()
        }
        //인증코드 확인 버튼 리스너
        v.btn_email_check.setOnClickListener {
            checkCode()
        }

        //키보드 내리기 함수
        settingMethodManager()

        // EditText 옵션 구현
        settingEditText()

        return v
    }





    //외부에서 imm을 가져오기 위한 함수
    private fun settingMethodManager(){
        val activity = activity as SignUpActivity
        mImm = activity.methodManagerToFragment()

        v.layout_email_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(v.edt_email.windowToken,0)
        }
    }

    // 키보드가 열리고 닫히는거 확인하는 함수
    private fun observeKeyboard() {
        activity?.let {
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow) {
                        // do checking EditText
                        v.edt_email.clearFocus()
                        v.edt_email_number.clearFocus()
                    }
                }
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun settingEditText(){
        v.edt_email.apply {
            textCheckListener {
                if (it.isNullOrEmpty()){
                    v.edt_email.background.setColorFilter(
                        resources.getColor(R.color.white_three),
                        PorterDuff.Mode.SRC_IN)
                }
            }
        }

        v.edt_email_number.apply {
            textCheckListener {
                if(it?.length == 5){
                    v.btn_email_check.isEnabled = true
                    v.btn_email_check.setTextColor(resources.getColor(R.color.white))
                }
                else{
                    v.btn_email_check.isEnabled = false
                    v.btn_email_check.setTextColor(resources.getColor(R.color.warm_grey))
                }
            }
        }

    }

    // 이메일에 인증번호를 보내는 버튼 누를 때 호출
    private fun checkEmail(){
        if(v.edt_email.text.toString().isEmpty()){
            v.edt_email.background.setColorFilter(
                resources.getColor(R.color.pinkish_tan),
                PorterDuff.Mode.SRC_IN)
            v.txt_email_failsend.visibility = View.VISIBLE
        }
        else{
            v.txt_email_failsend.visibility = View.INVISIBLE
            v.edt_email.background.setColorFilter(
                resources.getColor(R.color.white_three),
                PorterDuff.Mode.SRC_IN)
        }

    }

    // 이메일에서 받은 인증코드를 입력하는 버튼 누를 때 호출
    private fun checkCode(){
        if(v.edt_email_number.text?.length != 5){
            v.edt_email_number.background.setColorFilter(
                resources.getColor(R.color.pinkish_tan),
                PorterDuff.Mode.SRC_IN)
            v.txt_email_failcheck.visibility = View.VISIBLE
        }
        else{
            v.edt_email_number.background.setColorFilter(
                resources.getColor(R.color.white_three),
                PorterDuff.Mode.SRC_IN)
            v.txt_email_failcheck.visibility = View.INVISIBLE
        }
    }

}