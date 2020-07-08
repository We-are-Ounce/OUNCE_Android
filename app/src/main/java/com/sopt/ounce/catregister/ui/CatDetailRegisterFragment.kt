@file:Suppress("DEPRECATION")

package com.sopt.ounce.catregister.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.catregister.CatRegisterActivity
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_cat_detail_register.*
import kotlinx.android.synthetic.main.fragment_cat_detail_register.view.*
import kotlinx.android.synthetic.main.fragment_cat_profile_register.*
import kotlinx.android.synthetic.main.fragment_cat_profile_register.view.*
import kotlinx.android.synthetic.main.fragment_password.view.*

class CatDetailRegisterFragment : Fragment() {
    private lateinit var mContext : Context
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
        v = inflater.inflate(R.layout.fragment_cat_detail_register, container, false)

        //키보드 액션 설정
        observeKeyboard()
        settingMethodManager()



        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_catdetail.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_cat_age.windowToken,0)
        }

        radioGroup_detail.setOnCheckedChangeListener { _, id ->
            when (id){
                R.id.radiobutton_man -> {
                    radiobutton_man.setTextColor(resources.getColor(R.color.white))
                    radiobutton_woman.setTextColor(resources.getColor(R.color.greyish_brown))
                }
                R.id.radiobutton_woman -> {
                    radiobutton_man.setTextColor(resources.getColor(R.color.greyish_brown))
                    radiobutton_woman.setTextColor(resources.getColor(R.color.white))
                }
            }
        }

    }

    private fun observeKeyboard(){
        activity?.let{
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow){
                        v.edt_cat_age.clearFocus()
                        v.edt_cat_weight.clearFocus()
                    }

                }
        }

    }

    private fun settingMethodManager(){
        val activity = activity as CatRegisterActivity
        mImm = activity.setImmToFragment()

    }


}