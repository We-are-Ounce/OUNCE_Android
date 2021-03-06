package com.sopt.ounce.main.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.main.data.RequestFollowData
import com.sopt.ounce.main.data.RequestSelectedFilter
import com.sopt.ounce.main.data.ResponseOtherProfileData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.RcvItemDeco
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_other.*
import kotlinx.android.synthetic.main.bottomsheet_filter.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.properties.Delegates

class OtherActivity : AppCompatActivity() {

    private lateinit var mRecyclerAdapter: ReviewAdapter
    private lateinit var mFilterSheet: BottomSheetDialog
    private val mOunce = OunceServiceImpl
    private var mOtherProfileIdx by Delegates.notNull<Int>()
    private lateinit var mItem: Array<String>
    private val mProfileIdx = EasySharedPreference.getInt("profileIdx",1)


    // 리뷰 새로고침을 위한 카운트 (최신순)
    private var mPagingDate = 0
    // 리뷰 새로고침을 위한 카운트 (총점순)
    private var mPagingRating = 0
    //리뷰 새로고침을 위한 카운트 (기호도순)
    private var mPagingPrefer = 0

    //서버에 보낼 건식 습식 필터
    private var mFilterDry = mutableListOf<String>()

    //서버에 보낼 주재료 필터
    private var mFilterFoodType = mutableListOf<String>()

    //서버에 보낼 제조사 필터
    private var mFilterManu = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val intent = intent
        mOtherProfileIdx = intent.getIntExtra("otherProfile",0)
        mItem = resources.getStringArray(R.array.main_review_array)

        mFilterSheet = BottomSheetDialog(this)
        mFilterSheet.setContentView(R.layout.bottomsheet_filter)

        mRecyclerAdapter = ReviewAdapter(this)

        //스피너 설정
        val spinnerAdapter = ArrayAdapter(
            this,
            R.layout.main_custom_spinner, mItem
        )
        spinnerAdapter.setDropDownViewResource(R.layout.main_custom_dropdown)
        spinner_other.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?){
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    when(parent?.getItemAtPosition(position).toString()){
                        "날짜 순" -> {
                            mRecyclerAdapter.data.clear()
                            startServerReviewDate()
                        }

                        "총점 순" -> {
                            mRecyclerAdapter.data.clear()
                            startServerReviewRating()
                        }

                        "기호도 순" -> {
                            mRecyclerAdapter.data.clear()
                            startServerReviewPrefer()
                        }

                    }
                }
            }
        }

        //다른사람 데이터 보여주기 시작
        startServer()


        // 필터 바텀 시트 세팅
        settingFilter()

        // 리사이클러뷰 데이터 세팅
        initReviewRcv()


        //팔로우 버튼 클릭 시 변화
        btn_other_follow.setOnClickListener {
            if (it.isSelected) {
                startUnFollow()
                // true -> false로 가니까
                btn_other_follow.text = "팔로우"
            } else {
                //팔로우 신청 api 실행
                startFollow()
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


//        sticky_scroll_other.setOnScrollChangeListener(
//            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
//                if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
//
//                    when(v.spinner_main.selectedItem){
//                        "날짜 순" -> {
//                            startServerReviewDate()
//                        }
//                        "총점 순" -> {
//                            startServerReviewRating()
//                        }
//                        "기호도 순" -> {
//                            startServerReviewPrefer()
//                        }
//                    }
//
//                }
//            })


    }

    private fun startUnFollow() {
        mOunce.SERVICE.deleteFollow(
            RequestFollowData(
                mProfileIdx,
                mOtherProfileIdx
            )
        ).customEnqueue(
            onSuccess = {
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(this,"팔로우 취소 실패",Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun startFollow() {
        mOunce.SERVICE.postFollow(
            RequestFollowData(
                mProfileIdx,
                mOtherProfileIdx
            )
        ).customEnqueue(
            onSuccess = {
                "OunceServerSuccess".showLog("팔로우 성공")
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(this, "팔로우 신청 실패", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun startServerReviewDate() {
        mPagingRating = 0
        mPagingPrefer = 0

        "OunceServerState".showLog("다른 계정 프로필 리뷰 서버 통신 시작")

        mOunce.SERVICE.getOtherProfileReview(mOtherProfileIdx, 0, 100)
            .customEnqueue(
                onSuccess = {
                    "OunceServerSuccess".showLog("날짜 순 다른 프로필 리뷰 조회 성공\n ${it.data}")
                    mRecyclerAdapter.data.addAll(it.data)
                    "OunceServerSuccess".showLog("${mRecyclerAdapter.data}")
                    mRecyclerAdapter.notifyDataSetChanged()
                    mPagingDate += 10
                }
            )
    }

    private fun startServerReviewRating(){
//        mPagingDate = 0
//        mPagingPrefer = 0


        mOunce.SERVICE.getRatingReview(mOtherProfileIdx, 0, 100).customEnqueue(
            onSuccess = {
                "OunceServerSuccess".showLog("총점으로 리뷰 목록 불러오기 성공 \n ${it.data}")
                mRecyclerAdapter.data.addAll(it.data)
                mRecyclerAdapter.notifyDataSetChanged()
                //mPagingRating += 10
            },
            onError = {
                "OunceServerError".showLog("총점으로 리뷰 목록 불러오기 오류")
            }
        )
    }

    private fun startServerReviewPrefer(){
        mPagingDate = 0
        mPagingRating = 0

        mOunce.SERVICE.getPreferReview(mOtherProfileIdx, 0, 100).customEnqueue(
            onSuccess = {
                "OunceServerSuccess".showLog("선호도로 리뷰 목록 불러오기 성공 \n ${it.data}")
                mRecyclerAdapter.data.addAll(it.data)
                mRecyclerAdapter.notifyDataSetChanged()
                //mPagingPrefer += 10
            },
            onError = {
                "OunceServerError".showLog("선호도로 리뷰 목록 불러오기 오류")
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun startServer() {
        // myprofileIdx = 내 프로필 인덱스
        // otherIdx = intent로 받아온 인덱스
        mOunce.SERVICE.getOtherProfile(myprofileIdx = mProfileIdx, otherIdx = mOtherProfileIdx)
            .customEnqueue(
                onSuccess = {
                    "OunceServerSuccess".showLog("다른계정 프로필 조회 성공")
                    txt_other_reviewcount.text = "(${it.data.reviewCountAll})"

                    if (it.data.ischeck) {
                        btn_other_follow.isSelected = it.data.ischeck
                        btn_other_follow.text = "팔로우 취소"
                    } else {
                        btn_other_follow.isSelected = it.data.ischeck
                        btn_other_follow.text = "팔로우"
                    }


                    //프로필 뷰에 붙이기
                    settingDrawable(it.data.profileInfoArray[0])
                }
            )
    }

    //프로필 뷰에 데이터 붙이는 함수
    @SuppressLint("SetTextI18n")
    private fun settingDrawable(data: ResponseOtherProfileData.Data.Profile) {
        Glide.with(this)
            .load(data.profileImg)
            .error(R.drawable.img_cat)
            .into(img_other_profile)

        txt_other_profile.text = data.profileName
        txt_other_weight.text = "${data.profileWeight}kg"
        txt_other_age.text = "${data.profileAge}살"
        if (data.profileGender == "male") {
            if (data.profileNeutral == "true") {
                img_other_gender.setImageResource(R.drawable.ic_nuetrul_male)
            } else {
                img_other_gender.setImageResource(R.drawable.ic_male)
            }
        } else {
            if (data.profileNeutral == "true") {
                img_other_gender.setImageResource(R.drawable.ic_nuetrul_female)
            } else {
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
//        startServerReviewDate()
    }

    //필터 이미지 클릭 시 필터 바텀시트 호출 함수
    private fun showFilterSheet() {
        mFilterSheet.txt_filter_ok.setOnClickListener {
            mOunce.SERVICE.postSelectFiltering(
                1,
                RequestSelectedFilter(
                    foodManu = mFilterManu,
                    foodDry = mFilterDry,
                    foodMeat = mFilterFoodType
                )
            ).customEnqueue(
                onSuccess = {
                    "OunceServerSuccess".showLog("리뷰 필터 적용 완료")
                    mRecyclerAdapter.data.clear()
                    mRecyclerAdapter.data.addAll(it.data)
                    mRecyclerAdapter.notifyDataSetChanged()
                },
                onError = {
                    "OunceServerError".showLog("리뷰 필터 적용 실패")
                }
            )
            mFilterSheet.dismiss()
        }
        mFilterSheet.show()
    }

    //Chip 세팅 함수
    @Suppress("DEPRECATION")
    private fun settingFilter() {
        // 건식 습식 리스트
        val otherFoodType = listOf("건식", "습식")

        // 주재료 이름 리스트
        val otherIngredients = listOf(
            "연어", "칠면조", "소", "닭", "양", "토끼",
            "오리", "참치", "돼지", "해산물", "사슴", "캥거루", "기타"
        )

        mOunce.SERVICE.getFilterManu(mOtherProfileIdx).customEnqueue(
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}