package com.sopt.ounce.catregister


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.catregister.adapter.CatViewPagerAdapter
import com.sopt.ounce.util.StatusObject
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_cat_register.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_cat_profile_register.*

class CatRegisterActivity : AppCompatActivity() {
    private lateinit var mViewPagerAdapter : CatViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_register)

        // 상태바 아이콘 변경
        StatusObject.setStatusBar(this)
        //뷰페이저 설정
        initPager()

        //취소 버튼 누르면 액티비티 종료
        btn_catregister_cancle.setOnClickListener {
            finish()
        }

        //좌측 상단 뒤로가기 버튼 누를 시 뒤로 가기
        img_catregister_back.setOnClickListener {
            if(vp_catregister.currentItem == 1){
                vp_catregister.currentItem -= 1
            }
            else{
                finish()
            }
        }

        //확인 버튼 누를 시 다음 페이지 이동
        btn_catregister_ok.setOnClickListener {
            if(vp_catregister.currentItem == 0){
                vp_catregister.currentItem += 1
                buttonEnable(false)
            }
        }


    }

    private fun initPager(){
        mViewPagerAdapter = CatViewPagerAdapter(supportFragmentManager)
        vp_catregister.adapter = mViewPagerAdapter
        dot_catregister_indicator.setViewPager(vp_catregister)
        dot_catregister_indicator.dotsClickable = false
    }

    fun setImmToFragment() : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager


    @Suppress("DEPRECATION")
    fun buttonEnable(enable: Boolean) {
        if (enable) {
            btn_catregister_ok.apply {
                isEnabled = true
                setTextColor(resources.getColor(R.color.white))
            }
        } else {
            btn_catregister_ok.apply {
                isEnabled = false
                setTextColor(resources.getColor(R.color.greyish_two))
            }
        }

    }


}