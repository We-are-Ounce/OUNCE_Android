package com.sopt.ounce.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.util.RcvItemDeco
import kotlinx.android.synthetic.main.activity_other.*
import kotlinx.android.synthetic.main.bottomsheet_filter.*

class OtherActivity : AppCompatActivity() {

    private lateinit var mRecyclerAdapter: ReviewAdapter

    private lateinit var mFilterSheet: BottomSheetDialog

    //서버에 보낼 건식 습식 필터
    private var mFilterDry = mutableListOf<String>()

    //서버에 보낼 주재료 필터
    private var mFilterFoodType = mutableListOf<String>()

    //서버에 보낼 제조사 필터
    private var mFilterManu = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        mFilterSheet = BottomSheetDialog(this)
        mFilterSheet.setContentView(R.layout.bottomsheet_filter)

        mRecyclerAdapter = ReviewAdapter(this)

        // 필터 바텀 시트 세팅
        settingFilter()

        // 리사이클러뷰 데이터 세팅
        initReviewRcv()


        //팔로우 버튼 클릭 시 변화
        btn_other_follow.setOnClickListener {
            if(it.isSelected){
                // true -> false로 가니까
                btn_other_follow.text = "팔로우"
            }
            else{
                //false -> true 가니까
                btn_other_follow.text = "팔로우 취소"
            }

            it.isSelected = !it.isSelected
        }

        //이미지 버튼 클릭 시 필터 바텀 시트 호출
        img_other_filter.setOnClickListener {
            showFilterSheet()
        }

        //뒤로가기 버튼 클릭 시 액티비티 종료
        img_other_back.setOnClickListener {
            finish()
        }


    }

    private fun initReviewRcv() {
        rcv_other_review.apply {
            adapter = mRecyclerAdapter
            layoutManager = LinearLayoutManager(this@OtherActivity)
            addItemDecoration(RcvItemDeco(this@OtherActivity))
        }
    }

    //필터 이미지 클릭 시 필터 바텀시트 호출 함수
    private fun showFilterSheet() {
        mFilterSheet.txt_filter_ok.setOnClickListener {
            mFilterSheet.dismiss()
        }
        mFilterSheet.show()
    }

    //Chip 세팅 함수
    @Suppress("DEPRECATION")
    private fun settingFilter() {
        // 건식 습식 리스트
        val otherFoodType = listOf<String>("건식", "습식")

        // 주재료 이름 리스트
        val otherIngredients = listOf<String>(
            "연어", "칠면조", "소", "닭", "양", "토끼",
            "오리", "참치", "돼지", "해산물", "사슴", "캥거루", "기타"
        )

        // 제조사 이름 리스트
        val otherManu = listOf<String>(
            "GO!", "캣츠파인푸드", "테라펠리스", "나우"
        )

        //건식 습식 chip 생성
        for (word in otherFoodType) {
            val chip = chipSetting(word, mFilterDry)
            mFilterSheet.chipgroup_main_foodtype.addView(chip)
        }

        //주재료 chip 생성
        for (word in otherIngredients) {
            val chip = chipSetting(word, mFilterFoodType)
            mFilterSheet.chipgroup_main_ingredient.addView(chip)
        }

        //제조사 chip 생성 -> 서버 통신 받아서 유동적 해결
        for (word in otherManu) {
            val chip = chipSetting(word, mFilterManu)
            mFilterSheet.chipgroup_main_manu.addView(chip)
        }
    }

    //Chip 아이템 생성하는 함수
    @Suppress("DEPRECATION")
    private fun chipSetting(word: String, filterList: MutableList<String>): Chip {
        val c = Chip(this)
        c.apply {
            text = word
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            isCheckable = true
            isCheckedIconVisible = false
            setChipBackgroundColorResource(R.color.custom_filter)
            setTextAppearanceResource(R.style.filterTextStyle)
            setTextColor(resources.getColor(R.color.black_two))
            setChipStrokeColorResource(R.color.custom_filter_stroke)
            chipStrokeWidth = 6f
            setOnClickListener {
                if (isChecked) {
                    filterList.add(text.toString())
                    Log.d("List", "$filterList")

                } else {
                    filterList.remove(text.toString())
                    Log.d("List", "$filterList")

                }
            }
        }
        return c
    }

}