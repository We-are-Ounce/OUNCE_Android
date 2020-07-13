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
import com.sopt.ounce.R
import com.sopt.ounce.signup.data.UserInfoObject
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_id.view.*

class IdFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v : View
    private lateinit var mImm : InputMethodManager
    private lateinit var mActivity : SignUpActivity

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
                        resources.getColor(R.color.dark_peach),
                        PorterDuff.Mode.SRC_IN
                    )

                } else {
                    //길이 충족 할 경우
                    v.txt_id_notice.visibility = View.INVISIBLE
                    v.edt_id_input.background.setColorFilter(
                        resources.getColor(R.color.greyish_two),
                        PorterDuff.Mode.SRC_IN
                    )

                    mActivity.buttonEnable(true)

                }

                UserInfoObject.email = v.edt_id_input.text.toString()
            }

            setOnFocusChangeListener { _, has ->
                if(has){
                    v.edt_id_input.background.setColorFilter(
                        resources.getColor(R.color.black_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                else{
                    v.edt_id_input.background.setColorFilter(
                        resources.getColor(R.color.greyish_two),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }


            return v
    }

    //외부에서 imm을 가져오기 위한 함수
    private fun settingMethodManager(){
        mImm = mActivity.methodManagerToFragment()

        v.layout_id_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(v.edt_id_input.windowToken,0)
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