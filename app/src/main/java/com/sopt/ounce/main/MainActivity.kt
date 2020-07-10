package com.sopt.ounce.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
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
        val fragmentTransaction = mFm.beginTransaction()
        fragmentTransaction.add(R.id.fragment_main, HomeFragment())
//        fragmentTransaction.commit()




        bottom_main_appbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.main_search -> {
//                    Log.d("ClickCallBack","search")
                    if(!it.isChecked){
                        fragmentTransaction.replace(R.id.fragment_main, SearchFragment())
                        fragmentTransaction.commitAllowingStateLoss()
                        it.isChecked = true
                    }

                }
            }
            true
        }

        bottom_main_appbar.setNavigationOnClickListener {
            Log.d("ClickCallBack","logo")
            it.isSelected = !it.isSelected
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