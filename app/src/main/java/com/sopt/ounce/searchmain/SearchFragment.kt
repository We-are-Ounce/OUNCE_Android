package com.sopt.ounce.searchmain

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.viewpager.ViewPagerAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerTransformer
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        Log.d("onAttach", "Call Success")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        var viewPagerViewAdapter = ViewPagerViewAdapter(mContext)
//        viewPagerViewAdapter.apply {
//            onAddItem(
//                ViewPagerData(
//                    img_search_main_profile = R.drawable.img_card_cat,
//                    tv_search_main_cat_name = "봄이",
//                    tv_search_main_cat_similarity = "82",
//                    img_search_main_review_1 = R.drawable.img_card_cat,
//                    img_search_main_review_2 = R.drawable.img_card_cat,
//                    img_search_main_review_3 = R.drawable.img_card_cat
//                )
//            )
//            onAddItem(
//                ViewPagerData(
//                    img_search_main_profile = R.drawable.img_card_cat,
//                    tv_search_main_cat_name = "여름이",
//                    tv_search_main_cat_similarity = "82",
//                    img_search_main_review_1 = R.drawable.img_card_cat,
//                    img_search_main_review_2 = R.drawable.img_card_cat,
//                    img_search_main_review_3 = R.drawable.img_card_cat
//                )
//            )
//            onAddItem(
//                ViewPagerData(
//                    img_search_main_profile = R.drawable.img_card_cat,
//                    tv_search_main_cat_name = "가을이",
//                    tv_search_main_cat_similarity = "82",
//                    img_search_main_review_1 = R.drawable.img_card_cat,
//                    img_search_main_review_2 = R.drawable.img_card_cat,
//                    img_search_main_review_3 = R.drawable.img_card_cat
//                )
//            )
//            onAddItem(
//                ViewPagerData(
//                    img_search_main_profile = R.drawable.img_card_cat,
//                    tv_search_main_cat_name = "겨울이",
//                    tv_search_main_cat_similarity = "82",
//                    img_search_main_review_1 = R.drawable.img_card_cat,
//                    img_search_main_review_2 = R.drawable.img_card_cat,
//                    img_search_main_review_3 = R.drawable.img_card_cat
//                )
//            )
//            onAddItem(
//                ViewPagerData(
//                    img_search_main_profile = R.drawable.img_card_cat,
//                    tv_search_main_cat_name = "봄이",
//                    tv_search_main_cat_similarity = "82",
//                    img_search_main_review_1 = R.drawable.img_card_cat,
//                    img_search_main_review_2 = R.drawable.img_card_cat,
//                    img_search_main_review_3 = R.drawable.img_card_cat
//                )
//            )
//        }
        return inflater.inflate(
            R.layout.fragment_search,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewPagerAdapter = ViewPagerAdapter(view.context)
        viewPagerAdapter.img_search_main_profile_src = listOf(
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat)
        viewPagerAdapter.tv_search_main_cat_name_txt = listOf("봄이", "여름이", "가을이", "겨울이", "봄이")
        viewPagerAdapter.tv_search_main_cat_similarity_txt = listOf(
            "82",
            "92",
            "100",
            "1",
            "50"
        )
        viewPagerAdapter.img_search_main_review_1_src = listOf(
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat
        )
        viewPagerAdapter.img_search_main_review_2_src = listOf(
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat
        )
        viewPagerAdapter.img_search_main_review_3_src = listOf(
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat,
            R.drawable.img_card_cat
        )
        Log.d("어댑터 장착 준비", "성공")
        vp_search_main_viewpager.adapter = viewPagerAdapter
        vp_search_main_viewpager.clipToPadding = false
        vp_search_main_viewpager.clipChildren = false
        vp_search_main_viewpager.offscreenPageLimit = 3
        vp_search_main_viewpager.setPageTransformer(true, ViewPagerTransformer())

        val dpValue = 30
        val d = resources.displayMetrics.density
        val margin = dpValue*d.toInt()

        vp_search_main_viewpager.setPadding(margin,0,margin,0)
        vp_search_main_viewpager.pageMargin = margin/2
    }


}