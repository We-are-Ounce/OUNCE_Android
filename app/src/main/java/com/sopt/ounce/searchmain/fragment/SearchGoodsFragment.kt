package com.sopt.ounce.searchmain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.fragment_search_goods.*
import kotlinx.android.synthetic.main.item_search_main_goodssearch.*

class SearchGoodsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var spinnerData = listOf<String>("기호도 순", "총점 순")
        var spinnerAdapter = ArrayAdapter<String>(view.context,
            android.R.layout.simple_list_item_1,
            spinnerData)

        spn_search_goods_filter.adapter = spinnerAdapter
        spn_search_goods_filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                tv_result.text = "Nothing Selected"
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tv_result.text = spinnerData.get(position)
            }

        }
    }

    }