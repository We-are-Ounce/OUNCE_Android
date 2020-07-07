package com.sopt.ounce.catregister.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.ounce.R
import com.sopt.ounce.catregister.CatRegisterActivity
import kotlinx.android.synthetic.main.fragment_cat_profile_register.*


class CatProfileRegisterFragment : Fragment() {
    private lateinit var mContext : Context
    private lateinit var v : View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cat_profile_register, container, false)


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun observeKeyboard(){
        val activity = activity as CatRegisterActivity

    }


}