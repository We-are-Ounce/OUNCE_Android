package com.sopt.ounce.searchmain


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.searchmain.data.SpinnerAdapterViewModel
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.searchmain.data.foodsearch.RequestFoodSearchData
import com.sopt.ounce.searchmain.data.foodsearch.ResponseFoodSearchData
import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import com.sopt.ounce.searchmain.data.usersearch.RequestUserIdData
import com.sopt.ounce.searchmain.data.usersearch.ResponseUserSearchData
import com.sopt.ounce.searchmain.data.usersearch.UserData
import com.sopt.ounce.searchmain.fragment.SearchSimilarUserFragment
import com.sopt.ounce.searchmain.recyclerview.SearchGoodsAdapter
import com.sopt.ounce.searchmain.recyclerview.SearchUserAdapter
import com.sopt.ounce.searchmain.viewpager.SearchSimilarPagerAdapter
import com.sopt.ounce.searchmain.viewpager.SearchTapAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerTransformer
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_goods.*
import kotlinx.android.synthetic.main.fragment_search_goods.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    private lateinit var cView : View
    var isKeyboardFocused = false
    lateinit var mPagerAdapter : SearchSimilarPagerAdapter
    lateinit var mUserSearchData : ResponseUserSearchData
    lateinit var mFoodSearchData : ResponseFoodSearchData
    lateinit var mSearchTapAdapter: SearchTapAdapter
    var productQuery = ""


    var receiveDataArraySearch = ResponseRecommendCatsData.Data(
        listOf<ResponseRecommendCatsData.Data.RecommendFood>(
            ResponseRecommendCatsData.Data.RecommendFood("", -1)),
        listOf<ResponseRecommendCatsData.Data.ResultProfile>(
            ResponseRecommendCatsData.Data.ResultProfile(-1, "", "")
        ),
        listOf<Int>(0)
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_search,
            container,
            false
        )
        cView = inflater.inflate(R.layout.fragment_search_user,
            container,
            false
            )
        observeKeyboard()
        settingMethodManager()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fm_container.setOnClickListener {
            mInputMethodManager.hideSoftInputFromWindow(sv_search_main_search.windowToken,0)
        }
        //메인 화면 ViewPager 어댑터 부착
        initDataArray()

        vp_search_main_viewpager.clipToPadding = false
        vp_search_main_viewpager.clipChildren = false
        vp_search_main_viewpager.offscreenPageLimit = 1
        //메인 화면 PageTransFormer 부착
        vp_search_main_viewpager.setPageTransformer(true, ViewPagerTransformer())
        val DpValue = 80
        val DisplayDensity = resources.displayMetrics.density
        val margin = DpValue * DisplayDensity.toInt()

        vp_search_main_viewpager.setPadding(margin,0,margin,0)
        vp_search_main_viewpager.pageMargin = margin/32

        //ViewPager와 DotsIndicator 연동
        sv_search_main_search.findViewById<AutoCompleteTextView>(R.id.search_src_text).
        setTextColor(resources.getColor(R.color.greyish_brown))
        sv_search_main_search.findViewById<AutoCompleteTextView>(R.id.search_src_text).
        setHintTextColor(resources.getColor(R.color.pinkish_gray))
        val textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            4F,
            view.context.resources.displayMetrics)
        sv_search_main_search.findViewById<AutoCompleteTextView>(R.id.search_src_text).setTextSize(textSize)
        sv_search_main_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        //검색 창에 포커스 했을 때 화면 변화
        sv_search_main_search.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                if(hasFocus){
                    clayout_search_main_notfocus.visibility = View.GONE
                    clayout_search_main_focus.visibility = View.VISIBLE
                    isKeyboardFocused = true
                }
            }
        })

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(clayout_search_main_focus.visibility == View.VISIBLE){
                    clayout_search_main_notfocus.visibility = View.VISIBLE
                    clayout_search_main_focus.visibility = View.GONE
                    sv_search_main_search.setQuery("", false)
                    val mViewModel = ViewModelProviders.of(activity as MainActivity).get(SpinnerAdapterViewModel::class.java)
                    mViewModel.userQuery = ""
                    mViewModel.productQuery = ""
                }
                else if(clayout_search_main_focus.visibility == View.GONE){
                    ActivityCompat.finishAffinity(activity as MainActivity)
                }
            }

        })

        //검색 창 하단 ViewPager와 TabLayout 연동
        mSearchTapAdapter = SearchTapAdapter(view.context,childFragmentManager,tab_search_main_onfocus.tabCount)
        vp_search_main_search.adapter =  mSearchTapAdapter
        vp_search_main_search.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_search_main_onfocus))

        tab_search_main_onfocus.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

           override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_search_main_search.currentItem = tab_search_main_onfocus.selectedTabPosition
                sv_search_main_search.setQuery("", false)
            }
        })

        //검색기능 구현
        sv_search_main_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(tab_search_main_onfocus.selectedTabPosition == 0){
                    val ounce = OunceServiceImpl.SERVICE.postUserSearch(
                        RequestUserIdData(
                            userId = query!!,
                            pageStart = 1,
                            pageEnd = 100
                        )
                    )
                    ounce.customEnqueue(
                        onSuccess = {
                            val mViewModel = ViewModelProviders.of(activity as MainActivity).get(SpinnerAdapterViewModel::class.java)
                            mViewModel.userQuery == query!!
                            mUserSearchData = it
                            val mUserSearchAdapter = SearchUserAdapter(view.context)
                            val mActivity = activity as MainActivity
                            val searchUserRecyclerView = mActivity.findViewById<RecyclerView>(R.id.rv_search_user_searchresult)
                            searchUserRecyclerView.adapter = mUserSearchAdapter
                            mUserSearchAdapter.datas =  mUserSearchData.data as MutableList<UserData>
                            mUserSearchAdapter.notifyDataSetChanged()
                        },
                        onFaile = {
                        },
                        onError = {
                        }
                    )
                }
                else if(tab_search_main_onfocus.selectedTabPosition == 1){
                    val ounce = OunceServiceImpl.SERVICE.postReviewSortFavorite(
                        RequestFoodSearchData(
                            searchKeyword = query!!,
                            pageStart = 1,
                            pageEnd = 100
                        )
                    )
                    ounce.enqueue(object : Callback<ResponseFoodSearchData>{
                        override fun onFailure(call: Call<ResponseFoodSearchData>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseFoodSearchData>,
                            response: Response<ResponseFoodSearchData>
                        ) {
                            if(!response.body()!!.data.isNullOrEmpty()){
                                val mViewModel = ViewModelProviders.of(activity as MainActivity).get(SpinnerAdapterViewModel::class.java)
                                mViewModel.productQuery = query!!
                                //productQuery = query!!
                                mFoodSearchData = response.body()!!
                                val mFoodSearchAdapter = SearchGoodsAdapter(view.context)
                                val mActivity = activity as MainActivity
                                val searchFoodRecyclerView = mActivity.findViewById<RecyclerView>(R.id.rv_search_goods_goodsresult)
                                val searchSpinner = mActivity.findViewById<Spinner>(R.id.spn_search_goods_filter)
                                if(searchSpinner.visibility == View.GONE)
                                    searchSpinner.visibility = View.VISIBLE
                                searchFoodRecyclerView.adapter = mFoodSearchAdapter
                                mFoodSearchAdapter.datas = mFoodSearchData.data as MutableList<FoodData>
                                mFoodSearchAdapter.notifyDataSetChanged()
                            }
                        }
                    })
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initDataArray(){
        val ounce = OunceServiceImpl.SERVICE.requestRecommendCat(
            RequestRecommendCatsData(
                profileIdx = 2
            )
        )

        ounce.customEnqueue(
            onSuccess = {
                receiveDataArraySearch = it.data.copy()
                initViewPager()
                di_search_main_dotsindicator.setViewPager(vp_search_main_viewpager)
            },
            onError = {
            }
        )
    }

    private fun initViewPager(){
        //서버에서 데이터 받아
        mPagerAdapter = SearchSimilarPagerAdapter(childFragmentManager)
        for(i in receiveDataArraySearch.resultProfile.indices){
            var mFragment = SearchSimilarUserFragment()
            mFragment.img_search_main_profile_src = receiveDataArraySearch.resultProfile.get(i).profileImg
            mFragment.tv_search_main_cat_name_txt = receiveDataArraySearch.resultProfile.get(i).profileName
            mFragment.profileIdx = receiveDataArraySearch.resultProfile.get(i).profileIdx
            mFragment.tv_search_main_cat_similarity_txt = receiveDataArraySearch.similarity.get(i)
            for(arrIndices in receiveDataArraySearch.recommendFoodList.indices){
                if(mFragment.profileIdx == receiveDataArraySearch.recommendFoodList.get(arrIndices).profileIdx)
                    mFragment.img_search_main_review.add(receiveDataArraySearch.recommendFoodList.get(arrIndices).foodImg)
            }
            mPagerAdapter.addItem(mFragment)
        }
        mPagerAdapter.notifyDataSetChanged()
        vp_search_main_viewpager.adapter = mPagerAdapter
    }

    private fun observeKeyboard(){
        activity?.let{
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow){
                        mView.sv_search_main_search.clearFocus()
                    }

                }
        }
    }

    private fun settingMethodManager(){
        val activity = activity as MainActivity
        mInputMethodManager = activity.methodManagerToFragment()
    }
}