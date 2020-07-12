package com.sopt.ounce.searchmain

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.tabs.TabLayout
import com.sopt.ounce.R
import com.sopt.ounce.main.MainActivity
import com.sopt.ounce.searchmain.fragment.SearchSimilarUserFragment
import com.sopt.ounce.searchmain.recyclerview.SearchUserAdapter
import com.sopt.ounce.searchmain.recyclerview.SearchUserData
import com.sopt.ounce.searchmain.viewpager.SearchSimilarPagerAdapter
import com.sopt.ounce.searchmain.viewpager.SearchTapAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerTransformer
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_cat_detail_register.*
import kotlinx.android.synthetic.main.fragment_cat_detail_register.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_user.*
import kotlinx.android.synthetic.main.item_searchmain_similar.*
import java.io.Serializable

data class SimilarPagerData(
    var img_search_main_profile_src : Int,
    var tv_search_main_cat_name_txt : String,
    var tv_search_main_cat_similarity_txt : String,
    var img_search_main_review_1_src : Int,
    var img_search_main_review_2_src : Int,
    var img_search_main_review_3_src : Int
) : Serializable

class SearchFragment : Fragment() {
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    var isKeyboardFocused = false
    lateinit var mPagerAdapter : SearchSimilarPagerAdapter
    var receiveDataArray : ArrayList<SimilarPagerData> = ArrayList()

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
        //var viewPagerAdapter = registerViewPagerAdapter(view)
        //vp_search_main_viewpager.adapter = viewPagerAdapter
        initDataArray()
        initViewPager()
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
        di_search_main_dotsindicator.setViewPager(vp_search_main_viewpager)
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
        receiveDataArray.apply {
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "봄이",
                    tv_search_main_cat_similarity_txt = "82",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "여름이",
                    tv_search_main_cat_similarity_txt = "80",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "가을이",
                    tv_search_main_cat_similarity_txt = "90",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "겨울이",
                    tv_search_main_cat_similarity_txt = "12",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "참참참",
                    tv_search_main_cat_similarity_txt = "22",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
            add(
                SimilarPagerData(
                    img_search_main_profile_src = R.drawable.img_card_cat,
                    tv_search_main_cat_name_txt = "우울이",
                    tv_search_main_cat_similarity_txt = "52",
                    img_search_main_review_1_src = R.drawable.img_card_cat,
                    img_search_main_review_2_src = R.drawable.img_card_cat,
                    img_search_main_review_3_src = R.drawable.img_card_cat
                )
            )
        }
    }

    private fun initViewPager(){
        //서버에서 데이터 받아
        val adapter = SearchSimilarPagerAdapter(childFragmentManager)
        for(iteminit in receiveDataArray){
            var mFragment = SearchSimilarUserFragment()
            mFragment.img_search_main_profile_src = iteminit.img_search_main_profile_src
            mFragment.tv_search_main_cat_name_txt = iteminit.tv_search_main_cat_name_txt
            mFragment.tv_search_main_cat_similarity_txt = iteminit.tv_search_main_cat_similarity_txt + "%"
            mFragment.img_search_main_review_1_src = iteminit.img_search_main_review_1_src
            mFragment.img_search_main_review_2_src = iteminit.img_search_main_review_2_src
            mFragment.img_search_main_review_3_src = iteminit.img_search_main_review_3_src
            adapter.addItem(mFragment)
        }
        adapter.notifyDataSetChanged()
        vp_search_main_viewpager.adapter = adapter
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

//    private fun registerViewPagerAdapter(view: View) : ViewPagerAdapter{
//        var viewPagerAdapter = ViewPagerAdapter(view.context)
//        viewPagerAdapter.img_search_main_profile_src = listOf(
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat)
//        viewPagerAdapter.tv_search_main_cat_name_txt = listOf("봄이", "여름이", "가을이", "겨울이", "봄이")
//        viewPagerAdapter.tv_search_main_cat_similarity_txt = listOf(
//            "82",
//            "92",
//            "100",
//            "1",
//            "50"
//        )
//        viewPagerAdapter.img_search_main_review_1_src = listOf(
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat
//        )
//        viewPagerAdapter.img_search_main_review_2_src = listOf(
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat
//        )
//        viewPagerAdapter.img_search_main_review_3_src = listOf(
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat,
//            R.drawable.img_card_cat
//        )
//        return viewPagerAdapter
//    }
}