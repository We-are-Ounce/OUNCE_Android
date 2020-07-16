package com.sopt.ounce.main.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.main.data.ResponseOtherProfileData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.RcvItemDeco
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_other.*
import kotlinx.android.synthetic.main.bottomsheet_filter.*

class OtherActivity : AppCompatActivity() {

    private lateinit var mRecyclerAdapter: ReviewAdapter
    private lateinit var mFilterSheet: BottomSheetDialog
    private val mOunce = OunceServiceImpl

    // 리뷰 새로고침을 위한 카운트
    private var mPaging = 0

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

        //다른사람 데이터 보여주기 시작
        startServer()


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

    private fun startServerOtherReview() {
//        val profileIdx = EasySharedPreference.Companion.getInt("profileIdx",0)
        "OunceServerState".showLog("다른 계정 프로필 리뷰 서버 통신 시작")
        mOunce.SERVICE.getOtherProfileReview(1, mPaging,mPaging+9)
            .customEnqueue(
                onSuccess = {
                    "OunceServerSuccess".showLog("다른 프로필 리뷰 조회 성공\n ${it.data}")
                    mRecyclerAdapter.data.addAll(it.data)
                    mRecyclerAdapter.notifyDataSetChanged()
                    mPaging += 10
                }
            )
    }

    @SuppressLint("SetTextI18n")
    private fun startServer() {
        // myprofileIdx = 내 프로필 인덱스
        // otherIdx = intent로 받아온 인덱스
        mOunce.SERVICE.getOtherProfile(myprofileIdx = 26, otherIdx = 1)
            .customEnqueue(
                onSuccess = {
                    "OunceServerSuccess".showLog("다른계정 프로필 조회 성공")
                    txt_other_reviewcount.text="(${it.data.reviewCountAll})"
                    btn_other_follow.isSelected = it.data.ischeck

                    //프로필 뷰에 붙이기
                    settingDrawable(it.data.profileInfoArray[0])
                }
            )
    }

    //프로필 뷰에 데이터 붙이는 함수
    @SuppressLint("SetTextI18n")
    private fun settingDrawable(data : ResponseOtherProfileData.Data.Profile) {
        Glide.with(this)
            .load(data.profileImg)
            .error(R.drawable.img_cat)
            .into(img_other_profile)

        txt_other_profile.text = data.profileName
        txt_other_weight.text = "${data.profileWeight}kg"
        txt_other_age.text = "${data.profileAge}살"
        if(data.profileGender == "male"){
            if(data.profileNeutral == "true"){
                img_other_gender.setImageResource(R.drawable.ic_nuetrul_male)
            }
            else{
                img_other_gender.setImageResource(R.drawable.ic_male)
            }
        }else{
            if(data.profileNeutral == "true"){
                img_other_gender.setImageResource(R.drawable.ic_nuetrul_female)
            }
            else{
                img_other_gender.setImageResource(R.drawable.ic_female)
            }
        }

        txt_other_introduce.text = data.profileInfo
        txt_other_follower.text = "팔로워 ${data.follower}"
        txt_other_following.text = "팔로잉 ${data.following}"
    }

    private fun initReviewRcv() {
        rcv_other_review.apply {
            adapter = mRecyclerAdapter
            layoutManager = LinearLayoutManager(this@OtherActivity)
            addItemDecoration(RcvItemDeco(this@OtherActivity))
        }
        startServerOtherReview()
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

        mOunce.SERVICE.getFilterManu(1).customEnqueue(
            onSuccess = {
                "OunceServer".showLog("리뷰 필터 불러오기 성공 \n ${it.data}")
                //제조사 chip 생성 -> 서버 통신 받아서 유동적 해결
                for (word in it.data) {
                    "OunceStatus".showLog("리뷰 필터 데이터 단어 : ${word.foodManu}")
                    val chip = chipSetting(word.foodManu, mFilterManu)
                    mFilterSheet.chipgroup_main_manu.addView(chip)
                }
            },
            onError = {
                "OunceServerError".showLog("리뷰 필터 목록 ${it.code()}")
            }
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