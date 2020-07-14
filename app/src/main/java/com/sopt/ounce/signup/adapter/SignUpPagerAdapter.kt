package com.sopt.ounce.signup.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sopt.ounce.signup.ui.EmailCheckFragment
import com.sopt.ounce.signup.ui.IdFragment
import com.sopt.ounce.signup.ui.PasswordFragment

class SignUpPagerAdapter (fm : FragmentManager) : FragmentStatePagerAdapter(fm,
BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> EmailCheckFragment()
            1 -> IdFragment()
            else -> PasswordFragment()
        }
    }

    override fun getCount(): Int = 3

}