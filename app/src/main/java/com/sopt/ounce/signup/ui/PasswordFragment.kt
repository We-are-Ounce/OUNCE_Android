package com.sopt.ounce.signup.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.fragment_id.view.*

class PasswordFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v : View
    private lateinit var mImm : InputMethodManager

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_password, container, false)
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


}