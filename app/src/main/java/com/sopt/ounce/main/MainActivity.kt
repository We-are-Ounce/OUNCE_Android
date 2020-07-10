package com.sopt.ounce.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.FragmentManager
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.*
import com.sopt.ounce.searchmain.SearchFragment
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mFm : FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //상태바 업데이트
        StatusObject.setStatusBar(this)

        mFm = this.supportFragmentManager

//        fragmentTransaction.add(R.id.fragment_main, HomeFragment())
//        fragmentTransaction.commit()


        bottom_main_appbar.setOnMenuItemClickListener {
            val fragmentTransaction = mFm.beginTransaction()
            when (it.itemId){
                R.id.main_search -> {
//                    Log.d("ClickCallBack","search")
                    if(!it.isChecked) {
                        fragmentTransaction.replace(R.id.fragment_main, SearchFragment())
                            .commitAllowingStateLoss()
                        it.setIcon(R.drawable.ic_look)
                        it.isChecked = true

                        bottom_main_appbar.navigationIcon = ContextCompat.getDrawable(
                            this,R.drawable.ic_home_grey
                        )

                        bottom_main_appbar.isSelected = false
                    }

                }
            }
            true
        }


        bottom_main_appbar.setNavigationOnClickListener {

            if(!it.isSelected) {
                val fragmentTransaction = mFm.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_main, HomeFragment())
                    .commitAllowingStateLoss()

                bottom_main_appbar.navigationIcon = ContextCompat.getDrawable(
                    this, R.drawable.ic_home_black
                )

                bottom_main_appbar.menu.getItem(0).apply {
                    isChecked = false
                    setIcon(R.drawable.ic_search_grey)
                }

                bottom_main_appbar.isSelected = true
            }

        }



    }


    fun methodManagerToFragment() : InputMethodManager {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return imm
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
    }
}