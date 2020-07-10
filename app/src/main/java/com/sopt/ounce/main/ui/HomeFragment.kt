package com.sopt.ounce.main.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.BottomProfileAdapter
import com.sopt.ounce.main.adapter.ReviewAdapter
import com.sopt.ounce.main.data.BottomProfileData
import com.sopt.ounce.main.data.ReviewData
import com.sopt.ounce.util.RcvItemDeco
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.profile_bottomsheet.*


class HomeFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var v : View
    private lateinit var mItem :Array<String>
    private lateinit var mRecyclerAdapter : ReviewAdapter
    private lateinit var mProfileAdapter : BottomProfileAdapter
    private lateinit var mBottomSheet : BottomSheetDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBottomSheet = BottomSheetDialog(mContext)
        mProfileAdapter = BottomProfileAdapter(mContext)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        mItem = resources.getStringArray(R.array.main_review_array)

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
        mRecyclerAdapter.data = listOf(
            ReviewData("https://cdn.pixabay.com/photo/2020/07/04/06/40/clouds-5368435__340.jpg"
                ,"company1","title1","intro1",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2020/06/29/19/26/piano-5353974__340.jpg"
                ,"company2","title2","intro2",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/30/10/12/notredame-de-paris-4515298__340.jpg"
                ,"company3","title3","intro3",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/10/01/13/58/coffee-4518354__340.png"
                ,"company4","title4","intro4",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company5","title5","intro5",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company5","title5","intro5",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company5","title5","intro5",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company5","title5","intro5",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company9","title9","intro9",5,5),
            ReviewData("https://cdn.pixabay.com/photo/2019/09/24/12/50/sea-4501231__340.jpg"
                ,"company10","title10","intro10",5,5)
        )

        //고양이 이름 옆 아이콘 클릭 시 다른 고양이 프로필 선택 창 생성
        v.img_main_dropdown.setOnClickListener {
            showBottomSheet()
        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerAdapter.notifyDataSetChanged()


    }


    private fun showBottomSheet(){
        mBottomSheet.setContentView(R.layout.profile_bottomsheet)
        mBottomSheet.rcv_bottom_profile.apply{
            adapter = mProfileAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        mProfileAdapter.data = listOf(
            BottomProfileData(
                "https://cdn.pixabay.com/photo/2020/07/04/06/40/clouds-5368435__340.jpg",
            "title1",
            "intro1",
            false),
            BottomProfileData(
                "https://cdn.pixabay.com/photo/2020/07/04/06/40/clouds-5368435__340.jpg",
                "title2",
                "intro2",
            false)
        )
        mProfileAdapter.notifyDataSetChanged()
        mBottomSheet.show()

    }

}