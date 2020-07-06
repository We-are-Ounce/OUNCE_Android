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
import androidx.core.content.ContextCompat
import com.sopt.ounce.R
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_email_check.view.*
import kotlinx.android.synthetic.main.fragment_id.view.*

class IdFragment : Fragment() {
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
        v = inflater.inflate(R.layout.fragment_id, container, false)

        //키보드 show/hide 리스너
        observeKeyboard()

        //키보드 내리기 함수
        settingMethodManager()


        v.edt_id_input.apply {
            textCheckListener {
                if (it!!.isNotEmpty() && it!!.length < 5) {
                    //길이 5이상 써주세요
                    v.txt_id_notice.visibility = View.VISIBLE
                    v.edt_id_input.background.setColorFilter(
                        resources.getColor(R.color.pinkish_tan),
                        PorterDuff.Mode.SRC_IN
                    )

                } else {
                    //길이 충족 할 경우
                    v.txt_id_notice.visibility = View.INVISIBLE
                    v.edt_id_input.background.setColorFilter(
                        resources.getColor(R.color.white_three),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }


            return v
    }

    //외부에서 imm을 가져오기 위한 함수
    private fun settingMethodManager(){
        val activity = activity as SignUpActivity
        mImm = activity.methodManagerToFragment()

        v.layout_id_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(v.edt_id_input.windowToken,0)
            val id = v.edt_id_input.text.toString()
//            if (id.length > 5 ){
//                v.img_id_ok.visibility = View.VISIBLE
//            }
        }
    }

    // 키보드가 열리고 닫히는거 확인하는 함수
    private fun observeKeyboard() {
        activity?.let {
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow) {
                        // do checking EditText
                        v.edt_id_input.clearFocus()
                    }
                }
        }
    }

}