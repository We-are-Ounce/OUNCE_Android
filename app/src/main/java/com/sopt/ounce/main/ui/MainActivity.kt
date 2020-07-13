package com.sopt.ounce.main.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.SearchFragment
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mFm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //상태바 업데이트
        StatusObject.setStatusBar(this)

        mFm = this.supportFragmentManager
        val intent: Intent = intent
        val meOrYou: Boolean = intent.getBooleanExtra("me", true)

        if (meOrYou) {
            mFm.beginTransaction().apply {
                add(R.id.layout_main_content, HomeFragment())
                commit()
            }
        }
        else{
            mFm.beginTransaction().apply {
                add(R.id.layout_main_content, OtherUserFragment())
            }
        }


//        오른쪽 검색 창 클릭 시
            bottom_main_appbar.setOnMenuItemClickListener {
                val fragmentTransaction = mFm.beginTransaction()
                when (it.itemId) {
                    R.id.main_search -> {
//                    Log.d("ClickCallBack","search")
                        if (!it.isChecked) {
                            fragmentTransaction.replace(R.id.layout_main_content, SearchFragment())
//                            .commitAllowingStateLoss()
                                .commit()
                            it.setIcon(R.drawable.ic_look)
                            it.isChecked = true

                            bottom_main_appbar.navigationIcon = ContextCompat.getDrawable(
                                this, R.drawable.ic_home_unselected
                            )

                            bottom_main_appbar.isSelected = false
                        }

                    }
                }
                true
            }

//        왼쪽 홈 로고 클릭 시
            bottom_main_appbar.setNavigationOnClickListener {

                if (!it.isSelected) {
                    val fragmentTransaction = mFm.beginTransaction()
                    fragmentTransaction.replace(R.id.layout_main_content, HomeFragment())
//                    .commitAllowingStateLoss()
                        .commit()
                    bottom_main_appbar.navigationIcon = ContextCompat.getDrawable(
                        this, R.drawable.ic_home
                    )

                    bottom_main_appbar.menu.getItem(0).apply {
                        isChecked = false
                        setIcon(R.drawable.ic_look_unselected)
                    }

                    bottom_main_appbar.isSelected = true
                }

            }


        }


        fun methodManagerToFragment(): InputMethodManager {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            return imm
        }

}
