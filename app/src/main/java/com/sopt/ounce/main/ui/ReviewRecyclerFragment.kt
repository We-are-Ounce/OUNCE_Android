package com.sopt.ounce.main.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.fragment_review_recycler.*
import android.widget.SpinnerAdapter

class ReviewRecyclerFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var v : View
    private lateinit var mItem :Array<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_review_recycler, container, false)
        mItem = resources.getStringArray(R.array.main_review_array)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerAdapter = ArrayAdapter(mContext,
            R.layout.main_custom_spinner, mItem)

        spinnerAdapter.setDropDownViewResource(R.layout.main_custom_dropdown)
        spinner_main.apply {
            adapter = spinnerAdapter
            }
    }

}