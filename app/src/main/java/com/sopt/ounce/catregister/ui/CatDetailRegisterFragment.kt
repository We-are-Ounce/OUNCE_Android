package com.sopt.ounce.catregister.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.fragment_password.view.*

class CatDetailRegisterFragment : Fragment() {
    private lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cat_detail_register, container, false)

        return v
    }


}