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
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.google.android.material.tabs.TabLayout
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import com.sopt.ounce.searchmain.fragment.SearchSimilarUserFragment
import com.sopt.ounce.searchmain.network.recommendcat.RequestRecommendCatToServer
import com.sopt.ounce.searchmain.viewpager.SearchSimilarPagerAdapter
import com.sopt.ounce.searchmain.viewpager.SearchTapAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerTransformer
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class SearchFragment : Fragment() {
    val requestRecommendCatToServer = RequestRecommendCatToServer
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    var isKeyboardFocused = false
    lateinit var mPagerAdapter : SearchSimilarPagerAdapter
    //var receiveDataArraySearch : ArrayList<SearchSimilarUserData> = ArrayList()
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
        vp_search_main_viewpager.offscreenPageLimit = 2
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
                    Log.d("true", "1")
                    clayout_search_main_notfocus.visibility = View.VISIBLE
                    clayout_search_main_focus.visibility = View.GONE
                }
                else if(clayout_search_main_focus.visibility == View.GONE){
                    Log.d("true", "100100")
                    ActivityCompat.finishAffinity(activity as MainActivity)
                }
            }

        })

        //검색 창 하단 ViewPager와 TabLayout 연동
        vp_search_main_search.adapter = SearchTapAdapter(view.context,
            childFragmentManager,
            tab_search_main_onfocus.tabCount)
        vp_search_main_search.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_search_main_onfocus))
        tab_search_main_onfocus.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_search_main_search.currentItem = tab_search_main_onfocus.selectedTabPosition
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