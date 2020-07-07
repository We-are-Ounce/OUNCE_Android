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
import com.google.android.material.tabs.TabLayout
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.viewpager.SearchTapAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerAdapter
import com.sopt.ounce.searchmain.viewpager.ViewPagerTransformer
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {
    lateinit var mContext:Context
    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_search,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewPagerAdapter = registerViewPagerAdapter(view)
        vp_search_main_viewpager.adapter = viewPagerAdapter
        vp_search_main_viewpager.clipToPadding = false
        vp_search_main_viewpager.clipChildren = false
        vp_search_main_viewpager.offscreenPageLimit = 2
        vp_search_main_viewpager.setPageTransformer(true, ViewPagerTransformer())
        val DpValue = 70
        val DisplayDensity = resources.displayMetrics.density
        val margin = DpValue * DisplayDensity.toInt()

        vp_search_main_viewpager.setPadding(margin,0,margin,0)
        vp_search_main_viewpager.pageMargin = margin/2

        di_search_main_dotsindicator.setViewPager(vp_search_main_viewpager)

        sv_search_main_search.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                if(hasFocus){
                    clayout_search_main_notfocus.visibility = View.GONE
                    clayout_search_main_focus.visibility = View.VISIBLE
                }
                else{
                    clayout_search_main_notfocus.visibility = View.VISIBLE
                    clayout_search_main_focus.visibility = View.GONE
                }
            }
        })
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

    private fun registerViewPagerAdapter(view: View) : ViewPagerAdapter{
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
        return viewPagerAdapter
    }



}