package com.sopt.ounce.searchmain.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sopt.ounce.searchmain.fragment.SearchGoodsFragment
import com.sopt.ounce.searchmain.fragment.SearchUserFragment

class SearchTapAdapter(private val myContext: Context,
                       fm: FragmentManager,
                       internal var totalTabs: Int) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return SearchUserFragment()
            else -> return SearchGoodsFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}