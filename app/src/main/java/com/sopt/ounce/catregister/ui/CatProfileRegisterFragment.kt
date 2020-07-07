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
import kotlinx.android.synthetic.main.fragment_cat_profile_register.*
import kotlinx.android.synthetic.main.fragment_cat_profile_register.view.*
import kotlinx.android.synthetic.main.fragment_id.view.*


class CatProfileRegisterFragment : Fragment() {
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
        v = inflater.inflate(R.layout.fragment_cat_profile_register, container, false)
        //키보드 내리기 + 키보드 감지
        settingMethodManager()
        observeKeyboard()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_catprofile.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_catprofile_name.windowToken,0)
        }

    }

    private fun observeKeyboard(){
        activity?.let{
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow){
                        v.edt_catprofile_name.clearFocus()
                        v.edt_catprofile_explain.clearFocus()
                    }

                }
        }

    }

    private fun settingMethodManager(){
        val activity = activity as CatRegisterActivity
        mImm = activity.setImmToFragment()

    }


}