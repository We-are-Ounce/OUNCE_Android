package com.sopt.ounce.searchmain.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sopt.ounce.searchmain.fragment.SearchSimilarUserFragment

class SearchSimilarPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private var fragmentArray : ArrayList<SearchSimilarUserFragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragmentArray[position]
    override fun getCount(): Int = fragmentArray.size

    fun addItem(searchSimilarUserFragment : SearchSimilarUserFragment){
        fragmentArray.add(searchSimilarUserFragment)
    }

}