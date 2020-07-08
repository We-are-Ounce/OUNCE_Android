package com.sopt.ounce.searchmain.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.setPadding
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.recyclerview.*
import kotlinx.android.synthetic.main.fragment_search_goods.*
import kotlinx.android.synthetic.main.item_search_main_goodssearch.*

class SearchGoodsFragment : Fragment() {
    lateinit var searchGoodsAdapter: SearchGoodsAdapter
    var mGoodsData = mutableListOf<SearchGoodsData>()
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

        val spinnerData = listOf<String>("기호도 순", "총점 순")
        val spinnerAdapter = ArrayAdapter<String>(view.context,
            R.layout.item_spinner,
            spinnerData)
        spn_search_goods_filter.adapter = spinnerAdapter

        searchGoodsAdapter = SearchGoodsAdapter(view.context)
        rv_search_goods_goodsresult.adapter = searchGoodsAdapter
        loadGoodsResultData()
        rv_search_goods_goodsresult.addItemDecoration(SearchGoodsItemDecoration())
    }

    private fun loadGoodsResultData(){
        mGoodsData.apply {
            add(
                SearchGoodsData(
                    img_search_goods_goodsimage = R.drawable.img_card_cat,
                    tv_search_goods_company = "냐옹이 컴퍼니",
                    tv_search_goods_name = "내추럴발란스 연어",
                    tv_search_goods_review = "맛있게 먹네요^^",
                    tv_search_goods_staramount = "4.8",
                    tv_search_goods_heartamount = "3.2"
                )
            )
            add(
                SearchGoodsData(
                    img_search_goods_goodsimage = R.drawable.img_card_cat,
                    tv_search_goods_company = "냐옹이 컴퍼니",
                    tv_search_goods_name = "내추럴발란스 광",
                    tv_search_goods_review = "맛있게 먹네요^^",
                    tv_search_goods_staramount = "3.5",
                    tv_search_goods_heartamount = "3.0"
                )
            )
            add(
                SearchGoodsData(
                    img_search_goods_goodsimage = R.drawable.img_card_cat,
                    tv_search_goods_company = "냐옹이 컴퍼니",
                    tv_search_goods_name = "내추럴발란스 소고기",
                    tv_search_goods_review = "죤내 맛있게 먹네요^^",
                    tv_search_goods_staramount = "5.0",
                    tv_search_goods_heartamount = "1.6"
                )
            )
            add(
                SearchGoodsData(
                    img_search_goods_goodsimage = R.drawable.img_card_cat,
                    tv_search_goods_company = "냐옹이 컴퍼니",
                    tv_search_goods_name = "내추럴발란스 돼지고기",
                    tv_search_goods_review = "맛있게 먹네요^^",
                    tv_search_goods_staramount = "1",
                    tv_search_goods_heartamount = "2.4"
                )
            )
        }
        searchGoodsAdapter.datas = mGoodsData
        searchGoodsAdapter.notifyDataSetChanged()
    }

}