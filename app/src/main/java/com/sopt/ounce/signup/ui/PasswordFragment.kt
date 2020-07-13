@file:Suppress("DEPRECATION")

package com.sopt.ounce.signup.ui

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.sopt.ounce.R
import com.sopt.ounce.signup.data.UserInfoObject
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_password.*
import kotlinx.android.synthetic.main.fragment_password.view.*
import kotlinx.android.synthetic.main.fragment_password.view.edt_password_input

class PasswordFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v: View
    private lateinit var mImm: InputMethodManager
    private lateinit var mActivity: SignUpActivity
    private lateinit var mPassword : String
    private lateinit var mPasswordCheck : String

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as SignUpActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_password, container, false)
        settingMethodManager()
        observeKeyboard()

        // 비밀번호 입력창 길이 확인에 따른
//        밑줄 색상 변경과 문구 출력
        v.edt_password_input.apply {
            textCheckListener { it ->
                if (it.isNullOrEmpty() || it.length < 5) {
                    v.txt_password_issue.visibility = View.VISIBLE

                } else {
                    v.txt_password_issue.visibility = View.INVISIBLE

                }
                mPassword = it.toString()
            }

            setOnFocusChangeListener { _, has ->
                if (has) {
                    v.edt_password_input.background.setColorFilter(
                        resources.getColor(R.color.black_two),
                        PorterDuff.Mode.SRC_IN
                    )
                } else {
                    v.edt_password_input.background.setColorFilter(
                        resources.getColor(R.color.greyish_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }

        v.edt_password_check.apply{
            textCheckListener {
                mPasswordCheck = it.toString()
                checkPassword(mPassword, mPasswordCheck)
            }
        }


        return v
    }


    //외부에서 imm을 가져오기 위한 함수
    private fun settingMethodManager() {
        mImm = mActivity.methodManagerToFragment()

        v.layout_password_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(v.edt_password_input.windowToken, 0)
        }
    }

    private fun checkPassword(password: String, passwordCheck: String) {
        if (password == passwordCheck) {
            v.txt_password_checkissue.visibility = View.INVISIBLE
            v.edt_password_check.background.setColorFilter(
                resources.getColor(R.color.greyish_two),
                PorterDuff.Mode.SRC_IN
            )
            UserInfoObject.password = mPassword

            mActivity.buttonEnable(true)

        } else {
            v.txt_password_checkissue.visibility = View.VISIBLE
            v.edt_password_check.background.setColorFilter(
                resources.getColor(R.color.dark_peach), PorterDuff.Mode.SRC_IN
            )

            mActivity.buttonEnable(false)
        }

    }

    // 키보드가 열리고 닫히는거 확인하는 함수
    private fun observeKeyboard() {
        activity?.let {
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow) {
                        // do checking EditText
                        v.edt_password_input.clearFocus()
                        v.edt_password_check.clearFocus()
                    }
                }
        }
    }


}