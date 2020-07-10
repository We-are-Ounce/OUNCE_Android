package com.sopt.ounce.searchmain.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.sopt.ounce.R
import com.sopt.ounce.main.MainActivity
import com.sopt.ounce.searchmain.recyclerview.*
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_goods.*

class SearchGoodsFragment : Fragment() {
    lateinit var searchGoodsAdapter: SearchGoodsAdapter
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    private lateinit var iView : View
    var mGoodsData = mutableListOf<SearchGoodsData>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        iView = inflater.inflate(R.layout.fragment_search, container, false)
        observeKeyboard()
        settingMethodManager()
        mView = inflater.inflate(R.layout.fragment_search_goods, container, false)

        return mView
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

    private fun observeKeyboard(){
        activity?.let{
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow){
                        iView.sv_search_main_search.clearFocus()
                    }

                }
        }
    }

    private fun settingMethodManager(){
        val activity = activity as MainActivity
        mInputMethodManager = activity.methodManagerToFragment()
    }

}