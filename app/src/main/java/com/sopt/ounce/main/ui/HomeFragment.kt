package com.sopt.ounce.main.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.sopt.ounce.R
import com.sopt.ounce.catregister.ui.CatRegisterActivity
import com.sopt.ounce.main.adapter.BottomProfileAdapter
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.main.data.BottomProfileData
import com.sopt.ounce.main.data.ResponseFilterData
import com.sopt.ounce.main.data.ResponseMainProfileData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.RcvItemDeco
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.bottomsheet_filter.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.profile_bottomsheet.*


class HomeFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var v : View
    private lateinit var mItem :Array<String>
    private lateinit var mRecyclerAdapter : ReviewAdapter
    private lateinit var mProfileAdapter : BottomProfileAdapter
    private lateinit var mBottomsheetProfile : BottomSheetDialog
    private lateinit var mFilterSheet : BottomSheetDialog
    private val mOunce = OunceServiceImpl
    // 리뷰 새로고침을 위한 카운트
    private var mPaging = 1



    //서버에 보낼 건식 습식 필터
    private var mFilterDry = mutableListOf<String>()
    //서버에 보낼 주재료 필터
    private var mFilterFoodType = mutableListOf<String>()
    //서버에 보낼 제조사 필터
    private var mFilterManu = mutableListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBottomsheetProfile = BottomSheetDialog(mContext)
        mFilterSheet = BottomSheetDialog(mContext)
        mProfileAdapter = BottomProfileAdapter(mContext)

        activity?.onBackPressedDispatcher?.addCallback(this,
        object  : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                ActivityCompat.finishAffinity(activity as MainActivity)
            }
        })

    }

    @Suppress("DEPRECATION")
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        mItem = resources.getStringArray(R.array.main_review_array)

        //바텀시트 프로필 설정
        mBottomsheetProfile.setContentView(R.layout.profile_bottomsheet)
        //필터 바텀시트 설정
        mFilterSheet.setContentView(R.layout.bottomsheet_filter)

        // 스피너 설정
        val spinnerAdapter = ArrayAdapter(mContext,
            R.layout.main_custom_spinner, mItem)

        spinnerAdapter.setDropDownViewResource(R.layout.main_custom_dropdown)
        v.spinner_main.apply {
            adapter = spinnerAdapter
        }

        // 리사이클러뷰 설정
        mRecyclerAdapter = ReviewAdapter(mContext)
        v.rcv_main_review.apply {
            adapter = mRecyclerAdapter
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(RcvItemDeco(mContext))
        }

//        settingFilter()

        // 서버 통신 시작한 후 데이터들을 받아서 프로필 뷰에 뿌려주기
        startServerProfile()

        // 리뷰 데이터를 받아서 리사이클러 뷰로 뿌리기기
        startServerReview()

        //최하단으로 이동했을 때 10개 씩 데이터 추가
        v.sticky_scroll_main.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)){
                    startServerReview()
                }
        })

       return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerAdapter.notifyDataSetChanged()

        //고양이 이름 옆 아이콘 클릭 시 다른 고양이 프로필 선택 창 생성
        img_main_profile_dropdown.setOnClickListener {
            showBottomSheet()
        }

        // 필터 이미지 클릭시 필터 바텀 시트 생성
        img_main_filter.setOnClickListener {
            showFilterSheet()
        }


    }

    @Suppress("DEPRECATION")
    private fun settingFilter() {
        // 건식 습식 리스트
        val mainFoodType = listOf<String>("건식", "습식")

        // 주재료 이름 리스트
        val mainIngredients = listOf<String>(
            "연어", "칠면조", "소", "닭", "양", "토끼",
            "오리", "참치", "돼지", "해산물", "사슴", "캥거루", "기타"
        )

        //서버에서 제조사 이름 리스트 받아오기 시작
        val profileIdx = EasySharedPreference.Companion.getInt("profileIdx",0)
        mOunce.SERVICE.getFilterManu(profileIdx).customEnqueue(
            onSuccess = {
                "OunceServer".showLog("리뷰 필터 불러오기 성공 \n ${it.data}")
                //제조사 chip 생성 -> 서버 통신 받아서 유동적 해결
                for(word in it.data){
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
        for (word in mainFoodType) {
            val chip = chipSetting(word,mFilterDry)
            mFilterSheet.chipgroup_main_foodtype.addView(chip)
        }

        //주재료 chip 생성
        for (word in mainIngredients) {
            val chip = chipSetting(word,mFilterFoodType)
            mFilterSheet.chipgroup_main_ingredient.addView(chip)
        }


    }


    @Suppress("DEPRECATION")
    private fun chipSetting(word : String, filterList : MutableList<String>) : Chip{
        val c = Chip(mContext)
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

    private fun showFilterSheet(){
        settingFilter()
        mFilterSheet.txt_filter_ok.setOnClickListener {
            mFilterSheet.dismiss()
        }
        mFilterSheet.show()
    }


    private fun showBottomSheet(){
        mBottomsheetProfile.rcv_bottom_profile.apply{
            adapter = mProfileAdapter
            layoutManager = LinearLayoutManager(mContext)
        }
        val profileIdx = EasySharedPreference.Companion.getInt("profileIdx",0)

        mOunce.SERVICE.getConvesionProfile(profileIdx).customEnqueue(
            onSuccess = {
                "OunceStatus".showLog("프로필 바텀시트 호출 메세지 : ${it.message}")
                it.data?.let {data ->
                    mProfileAdapter.data = data
                }
            },
            onError = {
                "OunceError".showLog("프로필 바텀시트 호출 오류")
            }
        )

        mProfileAdapter.notifyDataSetChanged()

        mBottomsheetProfile.layout_bottomsheet_add_profile.setOnClickListener {
            val intent = Intent(mContext, CatRegisterActivity::class.java)
            startActivity(intent)
        }


        mBottomsheetProfile.show()

    }

    private fun startServerProfile(){
        val accessToken = EasySharedPreference.Companion.getString("accessToken","")
        val profileIdx = EasySharedPreference.Companion.getInt("profileIdx",0)

        mOunce.SERVICE.getMainProfile(accessToken,profileIdx).customEnqueue(
            onSuccess = {
                "OunceServerSuccess".showLog("메인프로필 화면 데이터 전달 성공 \n : ${it.data}")

                settingDraw(it.data[0])
            },
            onError = {
                "OunceServerError".showLog("메인프로필 화면 통신 오류 \n : ${it.code()}")
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun settingDraw(data : ResponseMainProfileData.Data){
        Glide.with(this)
            .load(data.profileImg)
            .error(R.drawable.img_cat)
            .into(v.img_main_profile)

        v.txt_main_profile.text = data.profileName
        v.txt_main_weight.text = "${data.profileWeight}kg"
        v.txt_main_age.text ="${data.profileAge}살"
        v.txt_main_introduce.text = data.profileInfo
        v.txt_main_follower.text = "팔로워 ${data.follower}"
        v.txt_main_following.text = "팔로잉 ${data.following}"

        if(data.profileGender == "male"){
            if(data.profileNeutral == "true"){
                v.img_main_gender.setImageResource(R.drawable.ic_nuetrul_male)
            }
            else{
                v.img_main_gender.setImageResource(R.drawable.ic_male)
            }
        }
        else{
            if(data.profileNeutral == "true"){
                v.img_main_gender.setImageResource(R.drawable.ic_nuetrul_female)
            }
            else{
                v.img_main_gender.setImageResource(R.drawable.ic_female)
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun startServerReview(){
        val profileIdx = EasySharedPreference.Companion.getInt("profileIdx",0)
        mOunce.SERVICE.getMainReview(profileIdx,mPaging,mPaging + 9).customEnqueue(
            onSuccess = {
                "OunceServerSuccess".showLog("메인 프로필 리뷰 목록 불러오기 성공 \n ${it.data}")
                mRecyclerAdapter.data.addAll(it.data)
                mRecyclerAdapter.notifyDataSetChanged()
                v.txt_main_reviewcount.text = "(${it.data.size})"
                mPaging += 10
            },
            onError = {
                "OunceServerError".showLog("메인 프로필 리뷰 목록 불러오기 오류")
            }
        )
    }

}