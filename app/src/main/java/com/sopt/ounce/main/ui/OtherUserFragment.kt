package com.sopt.ounce.main.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.util.RcvItemDeco
import kotlinx.android.synthetic.main.bottomsheet_filter.*
import kotlinx.android.synthetic.main.fragment_other_user.*
import kotlinx.android.synthetic.main.fragment_other_user.view.*


class OtherUserFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var v : View

    private lateinit var mRecyclerAdapter : ReviewAdapter

    private lateinit var mFilterSheet : BottomSheetDialog

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
        mFilterSheet = BottomSheetDialog(mContext)
        mRecyclerAdapter = ReviewAdapter(mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_other_user, container, false)
        mFilterSheet.setContentView(R.layout.bottomsheet_filter)
        // 필터 바텀 시트 세팅
        settingFilter()

        // 리사이클러뷰 데이터 세팅
        initReviewRcv()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //리사이클러뷰 보여주기
        mRecyclerAdapter.notifyDataSetChanged()


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
    }

//  리사이클러뷰 세팅 함수
    private fun initReviewRcv(){
        v.rcv_other_review.apply {
            adapter = mRecyclerAdapter
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(RcvItemDeco(mContext))
        }

//        mRecyclerAdapter.data = listOf(
//            ReviewData("https://cdn.pixabay.com/photo/2020/07/04/06/40/clouds-5368435__340.jpg"
//                ,"company1","title1","intro1",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2020/06/29/19/26/piano-5353974__340.jpg"
//                ,"company2","title2","intro2",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/30/10/12/notredame-de-paris-4515298__340.jpg"
//                ,"company3","title3","intro3",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/10/01/13/58/coffee-4518354__340.png"
//                ,"company4","title4","intro4",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company5","title5","intro5",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company5","title5","intro5",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company5","title5","intro5",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company5","title5","intro5",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company9","title9","intro9",5,5),
//            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
//                ,"company10","title10","intro10",5,5)
//        )

    }

    //필터 이미지 클릭 시 필터 바텀시트 호출 함수
    private fun showFilterSheet(){
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
            val chip = chipSetting(word,mFilterDry)
            mFilterSheet.chipgroup_main_foodtype.addView(chip)
        }

        //주재료 chip 생성
        for (word in otherIngredients) {
            val chip = chipSetting(word,mFilterFoodType)
            mFilterSheet.chipgroup_main_ingredient.addView(chip)
        }

        //제조사 chip 생성 -> 서버 통신 받아서 유동적 해결
        for(word in otherManu){
            val chip = chipSetting(word, mFilterManu)
            mFilterSheet.chipgroup_main_manu.addView(chip)
        }
    }

    //Chip 아이템 생성하는 함수
    @Suppress("DEPRECATION")
    private fun chipSetting(word : String, filterList : MutableList<String>) : Chip {
        val c = Chip(mContext)
        c.apply {
            text = word
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            isCheckable = true
            isCheckedIconVisible = false
            setChipBackgroundColorResource(R.color.custom_filter)
            setTextAppearanceResource(R.style.filterTextStyle)
            setTextColor(resources.getColor(R.color.dark))
            setChipStrokeColorResource(R.color.custom_filter_stroke)
            chipStrokeWidth = 6f
            setOnClickListener {
                if (isChecked) {
                    setTextColor(resources.getColor(R.color.white))
                    filterList.add(text.toString())
                    Log.d("List", "$filterList")

                } else {
                    setTextColor(resources.getColor(R.color.dark))
                    filterList.remove(text.toString())
                    Log.d("List", "$filterList")

                }
            }
        }

        return c
    }


}