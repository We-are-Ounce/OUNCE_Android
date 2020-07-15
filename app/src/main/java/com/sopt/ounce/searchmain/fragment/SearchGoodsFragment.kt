package com.sopt.ounce.searchmain.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.searchmain.data.SpinnerAdapterViewModel
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.searchmain.data.foodsearch.RequestFoodSearchData
import com.sopt.ounce.searchmain.recyclerview.*
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_goods.*

class SearchGoodsFragment : Fragment() {
    lateinit var searchGoodsAdapter: SearchGoodsAdapter
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    private lateinit var iView : View
    var mGoodsData = mutableListOf<FoodData>()

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
        val spinnerAdapter = object: ArrayAdapter<String>(view.context,
            R.layout.item_spinner,
            spinnerData){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView
                if (position == spn_search_goods_filter.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#ebe8e5"))
                }
                return view
            }
        }
        spn_search_goods_filter.adapter = spinnerAdapter

        searchGoodsAdapter = SearchGoodsAdapter(view.context)
        rv_search_goods_goodsresult.adapter = searchGoodsAdapter
        //loadGoodsResultData()
        rv_search_goods_goodsresult.addItemDecoration(SearchGoodsItemDecoration())

        spn_search_goods_filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent!!.getItemAtPosition(position).toString()
                val mViewModel = ViewModelProviders.of(activity as MainActivity).get(
                    SpinnerAdapterViewModel::class.java)
                if(selectedItem == "기호도 순"){
                    Log.d("Search - sortFavorite", "requestBefore")
                    val sortFavoriteSearch = OunceServiceImpl.SERVICE.postReviewSortFavorite(
                        RequestFoodSearchData(
                            searchKeyword = mViewModel.productQuery,
                            pageStart = 1,
                            pageEnd = 5
                        )
                    )
                    Log.d("Search - sortFavorite", "requestAfter")
                    sortFavoriteSearch.customEnqueue(
                        onSuccess = {
                            Log.d("Search - sortFavorite", "responseSuccessBefore")
                            Log.d("Search - favoriteDataBefore", "${searchGoodsAdapter.datas.toString()}")
                            mGoodsData = it.data as MutableList<FoodData>
                            searchGoodsAdapter.datas = mGoodsData
                            searchGoodsAdapter.notifyDataSetChanged()
                            Log.d("Search - sortFavorite", "responseSuccessAfter")
                            Log.d("Search - favoriteDataAfter", "${searchGoodsAdapter.datas.toString()}")
                        },
                        onError = {
                            Log.d("Search - sortFavorite", "responseError")
                        },
                        onFaile = {
                            Log.d("Search - sortFavorite", "responseFailure")
                        }
                    )
                }
                else if(selectedItem == "총점 순"){
                    Log.d("Search - sortTotal", "requestBefore")
                    val sortScoreSearch = OunceServiceImpl.SERVICE.postReviewSortTotalScore(
                        RequestFoodSearchData(
                            searchKeyword = mViewModel.productQuery,
                            pageStart = 1,
                            pageEnd = 5
                        )
                    )
                    Log.d("Search - sortTotal", "requestAfter")
                    sortScoreSearch.customEnqueue(
                        onSuccess = {
                            Log.d("Search - sortTotal", "reponseSuccessBefore")
                            Log.d("Search - totalDataBefore", "${searchGoodsAdapter.datas.toString()}")
                            mGoodsData = it.data as MutableList<FoodData>
                            searchGoodsAdapter.datas = mGoodsData
                            searchGoodsAdapter.notifyDataSetChanged()
                            Log.d("Search - sortTotal", "reponseSuccessAfter")
                            Log.d("Search - totalDataAfter", "${searchGoodsAdapter.datas.toString()}")
                        },
                        onError = {
                            Log.d("Search - sortTotal", "reponseError")
                        },
                        onFaile = {
                            Log.d("Search - sortTotal", "reponseFailure")
                        }
                    )
                }
            }

        }
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