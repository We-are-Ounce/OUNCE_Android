package com.sopt.ounce.record.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.sopt.ounce.R
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.FeatureAdapter
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.record.data.RequestModifyData
import com.sopt.ounce.record.data.ResponseDetailData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.StatusObject
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_record_modify.*


class RecordModifyActivity : AppCompatActivity() {


    //서비스 호출
    private val mModifyRequest = OunceServiceImpl
    private var mReviewIdx = 0

    private lateinit var mFeatureAdapter: FeatureAdapter


    // 수정될 데이터 저장 공간
    private lateinit var reviewData: ResponseDetailData.Data

    //액세스 토큰
    private val mAccessToken  = EasySharedPreference.Companion.getString("accessToken","")
    //프로필 인덱스
    private val mProfileIdx = EasySharedPreference.Companion.getInt("profileIdx",1)

    //private val mDeleteData = OunceServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_modify)

        StatusObject.setStatusBar(this)

        val intent = intent
        mReviewIdx = intent.getIntExtra("reviewIdx", 0)

        val isMe = intent.getBooleanExtra("isMy", true)

        if (isMe) {
            //내 리뷰 프로필이면 상단 텍스트 이름 변경
            txt_update_myrecord.text = "나의 기록"
            btn_record_popup.visibility = View.VISIBLE
        } else {
            txt_update_myrecord.text = ""
        }

        enableChange(false, layout_modify_parent)

        startServerReview()

        val popup = PopupMenu(this, btn_record_popup)
        popup.inflate(R.menu.record_popup)
        popup.setOnMenuItemClickListener {
            when (it.title) {
                "수정" -> {
                    record_update_button.visibility = View.VISIBLE
                    enableChange(true, layout_modify_parent)
                }
                "삭제" -> {

                    mModifyRequest.SERVICE.deleteDataReview(
                        mAccessToken,
                        mProfileIdx,
                        mReviewIdx
                    ).customEnqueue(
                        onSuccess = {
                            Toast.makeText(
                                this@RecordModifyActivity,
                                "내가 쓴 리뷰 삭제 성공",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        },
                        onError = {
                            Toast.makeText(
                                this@RecordModifyActivity,
                                "리뷰 삭제 권한이 없습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    )
                }

            }
            true
        }


        btn_record_popup.setOnClickListener {

            popup.show()
        }

        //총점을 받아오는 리뷰 수정 클릭 리스너
        ratingBar_update_score.setOnRatingChangeListener {
            reviewData.reviewRating = it.toInt().toString()
            "RecordStatus".showLog("${it.toInt()}")
        }
        //기호도를 받아오는 리뷰 수정 클릭 리스너
        ratingBar_update_prefer.setOnRatingChangeListener {
            reviewData.reviewRating = it.toInt().toString()
            "RecordStatus".showLog("${it.toInt()}")
        }






        record_update_eye_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                reviewData.reviewEye = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                reviewData.reviewEye = 1
            }
        }

        record_update_ear_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                reviewData.reviewEar = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                reviewData.reviewEar = 1
            }
        }

        record_update_fur_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                reviewData.reviewHair = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                reviewData.reviewHair = 1
            }
        }

        record_update_vomit_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                reviewData.reviewVomit = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                reviewData.reviewVomit = 1
            }
        }
        record_x_btn.setOnClickListener {
            finish()
        }

        //리뷰 수정
        //리뷰 수정 데이터 불러오기
        record_update_button.setOnClickListener {
            mModifyRequest.SERVICE.putUpdateReview(
                accessToken = mAccessToken,
                reviewIdx = mReviewIdx,
                body = RequestModifyData(
                    reviewRating = reviewData.reviewRating.toInt(),
                    reviewPrefer = reviewData.reviewPrefer.toInt(),
                    reviewInfo = edt_update_single.text.toString(),
                    reviewMemo = edt_update_memo.text.toString(),
                    reviewStatus = reviewData.reviewStatus,
                    reviewSmell = reviewData.reviewSmell,
                    reviewEye = reviewData.reviewEye,
                    reviewEar = reviewData.reviewEar,
                    reviewHair = reviewData.reviewHair,
                    reviewVomit = reviewData.reviewVomit,
                    foodIdx = reviewData.foodIdx,
                    profileIdx = EasySharedPreference.Companion.getInt("profileIdx", 1)
                )
            ).customEnqueue(
                onSuccess = {
                    // 서버통신에 성공해서 데이터를 받아왔을 때
                    Toast.makeText(this@RecordModifyActivity, "내가 쓴 리뷰 수정 성공", Toast.LENGTH_SHORT)
                        .show()
                    finish()

                },
                onError = {
                    Toast.makeText(this@RecordModifyActivity, "리뷰 수정 권한이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            )
        }


    }

    private fun initReviewRcv(data: ResponseDetailData.Data) {
        mFeatureAdapter = FeatureAdapter(this)
        rcv_record_update_feature.apply {
            adapter = mFeatureAdapter
            layoutManager = LinearLayoutManager(
                this@RecordModifyActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(RecordItemDecoration(this@RecordModifyActivity))
        }
        //어댑터 구현하기
        if (data.foodMeat2.isNullOrBlank()) {
            mFeatureAdapter.data = listOf(
                FeatureData(data.foodDry),
                FeatureData(data.foodMeat1)
            )
        } else {
            mFeatureAdapter.data = listOf(
                FeatureData(data.foodDry),
                FeatureData(data.foodMeat1),
                FeatureData(data.foodMeat2!!)
            )
        }

        mFeatureAdapter.notifyDataSetChanged()
    }

    private fun startServerReview() {
        "OunceReviewModify".showLog("$mReviewIdx")
        mModifyRequest.SERVICE.getDetailReview(mReviewIdx).customEnqueue(
            onSuccess = {
                "OunceReviewModifyServerSuccess".showLog(it.message)
                it.data.let { data ->
                    reviewData = data[0]
                    initReviewDataInLayout(data[0])
                    initReviewRcv(data[0])
                }
            },
            onError = {
                "OunceReviewModifyServerError".showLog("${it.code()}")
            }
        )

    }

    private fun initReviewDataInLayout(foodData: ResponseDetailData.Data) {

        Glide.with(this)
            .load(foodData.foodImg)
            .error(R.drawable.record_image_img_empty)
            .into(image_Preview2)

        txt_record_update_company.text = foodData.foodManu
        txt_record_update_name.text = foodData.foodName

        ratingBar_update_score.setStar(foodData.reviewRating.toFloat())
        ratingBar_update_prefer.setStar(foodData.reviewPrefer.toFloat())

        edt_update_single.setText(foodData.reviewInfo)

        when (foodData.reviewStatus) {
            1 -> {
                record_update_chip.isChecked = true
            }
            2 -> {
                record_update_chip_two.isChecked = true
            }
            3 -> {
                record_update_chip_three.isChecked = true
            }
            4 -> {
                record_update_chip_four.isChecked = true
            }
            5 -> {
                record_update_chip_five.isChecked = true
            }
        }

        when (foodData.reviewSmell) {
            1 -> {
                record_smellchip_update.isChecked = true
            }
            2 -> {
                record_smellchip_update_two.isChecked = true
            }
            3 -> {
                record_smellchip_update_three.isChecked = true
            }
            4 -> {
                record_smellchip_update_four.isChecked = true
            }
            5 -> {
                record_smellchip_update_five.isChecked = true
            }
        }

        if (foodData.reviewEye == 1) {
            record_update_eye_btn.isSelected = true
        }

        if (foodData.reviewEar == 1) {
            record_update_ear_btn.isSelected = true
        }

        if (foodData.reviewHair == 1) {
            record_update_fur_btn.isSelected = true
        }

        if (foodData.reviewVomit == 1) {
            record_update_vomit_btn.isSelected = true
        }

        edt_update_memo.setText(foodData.reviewMemo)
    }


    private fun enableChange(b : Boolean, vg : ViewGroup){
        for(i in 0 until vg.childCount){
            val child = vg.getChildAt(i)
            child.isEnabled = b
            if(child is AppBarLayout){
                continue
            }

            if(child is ViewGroup){
                enableChange(b , child)
            }
        }
    }


}