package com.sopt.ounce.catregister.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sopt.ounce.catregister.ui.CatDetailRegisterFragment
import com.sopt.ounce.catregister.ui.CatProfileRegisterFragment

class CatViewPagerAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm,
BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> CatProfileRegisterFragment()
            else -> CatDetailRegisterFragment()
        }
    }

    override fun getCount(): Int = 2
}