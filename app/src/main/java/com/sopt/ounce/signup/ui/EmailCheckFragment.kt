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
import com.sopt.ounce.server.EmailCheckServiceImpl
import com.sopt.ounce.signup.data.RequestEmailData
import com.sopt.ounce.signup.data.UserInfoObject
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_email_check.*
import kotlinx.android.synthetic.main.fragment_email_check.view.*
import java.util.regex.Pattern
import kotlin.properties.Delegates


class EmailCheckFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v: View
    private lateinit var mImm: InputMethodManager
    private lateinit var mActivity: SignUpActivity
    private var mCode by Delegates.notNull<Int>()
    private lateinit var mEmail : String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as SignUpActivity
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
    private fun settingMethodManager() {
        mImm = mActivity.methodManagerToFragment()

        v.layout_email_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(v.edt_email.windowToken, 0)
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
                        v.edt_email.background.setColorFilter(
                            resources.getColor(R.color.greyish_two),
                            PorterDuff.Mode.SRC_IN
                        )
                        v.edt_email_number.clearFocus()
                        v.edt_email_number.background.setColorFilter(
                            resources.getColor(R.color.greyish_two),
                            PorterDuff.Mode.SRC_IN
                        )
                    }
                }
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun settingEditText() {
        //이메일 형식 체크 변수
        val p = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
        )

        v.edt_email.apply {
            textCheckListener {
                val m = p.matcher(it.toString())
                if (m.matches()) {
                    v.btn_email_send.apply {
                        isEnabled = true
                        setTextColor(resources.getColor(R.color.white))
                    }
                } else {
                    v.btn_email_send.apply {
                        isEnabled = false
                        setTextColor(resources.getColor(R.color.greyish_two))
                    }
                }
            }

            setOnFocusChangeListener { _, has ->
                if (has) {
                    v.edt_email.background.setColorFilter(
                        resources.getColor(R.color.black_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }

        v.edt_email_number.apply {
            textCheckListener {
                if (it?.length == 6) {
                    v.btn_email_check.isEnabled = true
                    v.btn_email_check.setTextColor(resources.getColor(R.color.white))
                } else {
                    v.btn_email_check.isEnabled = false
                    v.btn_email_check.setTextColor(resources.getColor(R.color.greyish_two))
                }
            }
        }

    }

    // 이메일에 인증번호를 보내는 버튼 누를 때 호출
    private fun checkEmail() {
        mEmail = v.edt_email.text.toString()
        //6자리 난수 생성
        mCode = (100000..999999).random()

        val request = EmailCheckServiceImpl
        request.service.postEmail(
            RequestEmailData(
                v.edt_email.text.toString(),
                "OUNCE 회원가입 인증해주세요.",
                "인증번호를 입력해주세요 : $mCode"
            )
        ).customEnqueue(
            onSuccess = {
                v.txt_email_failsend.text = "인증번호를 발송했습니다."
                v.txt_email_failsend.visibility = View.VISIBLE
            },
            onError = {
                v.txt_email_failsend.text = "올바른 이메일을 입력해주세요."
                v.txt_email_failsend.visibility = View.VISIBLE
            }
        )

    }

    // 이메일에서 받은 인증코드를 입력하는 버튼 누를 때 호출
    private fun checkCode() {
        if (mCode == v.edt_email_number.text.toString().toInt()) {
            v.txt_email_failcheck.text = "인증이 완료되었습니다."
            v.txt_email_failcheck.visibility = View.VISIBLE
            UserInfoObject.email = mEmail
            mActivity.buttonEnable(true)
        }
        else {
            v.edt_email_number.background.setColorFilter(
                resources.getColor(R.color.dark_peach),
                PorterDuff.Mode.SRC_IN
            )
            v.txt_email_failcheck.text = "인증번호가 틀렸습니다."
            v.txt_email_failcheck.visibility = View.VISIBLE
        }
    }

}