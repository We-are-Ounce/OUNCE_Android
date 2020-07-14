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
import com.sopt.ounce.catregister.data.CatInfoData
import com.sopt.ounce.util.showLog
import com.sopt.ounce.util.textCheckListener
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_cat_detail_register.*
import kotlinx.android.synthetic.main.fragment_cat_detail_register.view.*

class CatDetailRegisterFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v: View
    private lateinit var mImm: InputMethodManager
    private lateinit var mActivity: CatRegisterActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as CatRegisterActivity
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
            mImm.hideSoftInputFromWindow(edt_cat_age.windowToken, 0)
        }

        radioGroup_detail.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radiobutton_man -> {
                    CatInfoData.profileGender = "male"

                    "CatInfoData".showLog("CatInfoData.profileGender : ${CatInfoData.profileGender}")
                }
                R.id.radiobutton_woman -> {
                    CatInfoData.profileGender = "femail"

                    "CatInfoData".showLog("CatInfoData.profileGender : ${CatInfoData.profileGender}")
                }
            }
            checkData()
        }

        check_newtral.setOnCheckedChangeListener { _, check ->
            if (check) {
                CatInfoData.profileNeutral = "true"
                "CatInfoData".showLog("CatInfoData.profileNeutral : ${CatInfoData.profileNeutral}")
            } else {
                CatInfoData.profileNeutral = "false"
                "CatInfoData".showLog("CatInfoData.profileNeutral : ${CatInfoData.profileNeutral}")
            }
            checkData()
        }

        edt_cat_age.apply {
            textCheckListener {
                CatInfoData.profileAge = it.toString()
                checkData()
            }
        }

        edt_cat_weight.apply {
            textCheckListener {
                CatInfoData.profileWeight = it.toString()
                checkData()
            }
        }

    }

    private fun checkData() {
        if (CatInfoData.profileAge.isBlank() ||
            CatInfoData.profileGender.isBlank() ||
            CatInfoData.profileNeutral.isBlank() ||
            CatInfoData.profileWeight.isBlank()
        ) {
            mActivity.buttonEnable(false)
        } else {
            mActivity.buttonEnable(true)
        }
    }

    private fun observeKeyboard() {
        activity?.let {
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow) {
                        v.edt_cat_age.clearFocus()
                        v.edt_cat_weight.clearFocus()
                    }

                }
        }

    }

    private fun settingMethodManager() {
        mImm = mActivity.setImmToFragment()

    }


}