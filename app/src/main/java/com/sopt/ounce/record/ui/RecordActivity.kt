package com.sopt.ounce.record.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.record.data.RequestRecordReviewData
import com.sopt.ounce.record.data.ResponseRecordReviewData
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.FeatureAdapter
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.record.data.RecordSearchFoodData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class RecordActivity : AppCompatActivity() {

    private lateinit var mFeatureAdapter: FeatureAdapter

    // 총점 기호도 체크 변수
    private var mTotal by Delegates.notNull<Int>()
    private var mFavor by Delegates.notNull<Int>()

    // 트러블 현상에 관한 변수
    private var mEye = 0
    private var mEar = 0
    private var mFur = 0
    private var mVomit = 0

    // 변 상태에 관한 변수
    private var mStatus = 0
    private var mSmell = 0

    //서버 통신, 싱글톤 그대로 가져옴
    private val mRecordRequest = OunceServiceImpl

    private lateinit var foodData: RecordSearchFoodData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
//        ratingBar.setStarFillDrawable(resources.getDrawable(R.drawable.ic_evaluate_full))
//        ratingBar.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_total_unselected))
//        ratingBar2.setStarFillDrawable(resources.getDrawable(R.drawable.ic_favorite_select))
//        ratingBar2.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_favorite_unselected))

        val intent = intent
        foodData = intent.getSerializableExtra("foodItem") as RecordSearchFoodData

        Glide.with(this).load(foodData.foodImg).into(image_Preview)
        txt_record_company.text = foodData.foodManu
        txt_record_name.text = foodData.foodName
        val foodIdx = foodData.foodIdx

        //총점을 받아오는 클릭 리스너
        ratingBar.setOnRatingChangeListener {
            mTotal = it.toInt()
            "RecordStatus".showLog("${it.toInt()}")
        }
        //기호도를 받아오는 클릭 리스너
        ratingBar2.setOnRatingChangeListener {
            mFavor = it.toInt()
            "RecordStatus".showLog("${it.toInt()}")
        }

        initFeatureRcv()

        // 트러블 체크 리스너
        record_eye_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                mEye = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                mEye = 1
            }
        }

        record_ear_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                mEar = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                mEar = 1
            }
        }

        record_fur_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                mFur = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                mFur = 1
            }
        }

        record_vomit_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
                mVomit = 0
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
                mVomit = 1
            }
        }

        //뒤로가기 클릭 시 뒤로 이동
        record_goback_btn.setOnClickListener {
            finish()
        }

        setChipCheckListener()

        btn_record_save.setOnClickListener {
            val data = RequestRecordReviewData(
                mTotal,
                mFavor,
                edt_record_sigle_memo.text.toString(),
                memo_edt.text.toString(),
                mStatus,
                mSmell,
                mEye,
                mEar,
                mFur,
                mVomit,
                foodIdx,
                EasySharedPreference.getInt("profileIdx", 1)
            )

            val postAddReview = mRecordRequest.SERVICE.postAddReview(
                EasySharedPreference.Companion.getString("accessToken", ""),
                data
            )

            //EasySharedPreference.Companion.getString("accessToken","")

            //비동기통신에서 오류처리
            "Record - call".showLog("here0")
            postAddReview.enqueue(object : Callback<ResponseRecordReviewData> {
                override fun onFailure(call: Call<ResponseRecordReviewData>, t: Throwable) {
                    Log.e("recordAdd-failure:", t.toString())
                }

                override fun onResponse(
                    call: Call<ResponseRecordReviewData>,
                    responseRecord: Response<ResponseRecordReviewData>
                ) {
                    "Record - call".showLog("here1")
                    if (responseRecord.isSuccessful) {
                        //성공했을 때 처리
                        if (responseRecord.body()!!.success) {
                            "Record - success".showLog("here2")
                            Toast.makeText(this@RecordActivity, "리뷰 등록 완료", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(this@RecordActivity, "리뷰 등록 실패", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }

                    }
                }


            })
        }

    }

    private fun setChipCheckListener() {
        chipGroup_status.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == -1) {
                mStatus = 0
            }
        }
        record_chip.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mStatus = 1
            }
        }
        record_chip_two.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mStatus = 2
            }
        }
        record_chip_three.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mStatus = 3
            }
        }
        record_chip_four.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mStatus = 4
            }
        }
        record_chip_five.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mStatus = 5
            }
        }


        chipSmellGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == -1) {
                mSmell = 0
            }
        }

        record_smellchip.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mSmell = 1
            }
        }
        record_smellchip_two.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mSmell = 2
            }
        }
        record_smellchip_three.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mSmell = 3
            }
        }
        record_smellchip_four.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mSmell = 4
            }
        }
        record_smellchip_five.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                mSmell = 5
            }
        }


    }

    private fun initFeatureRcv() {
        mFeatureAdapter = FeatureAdapter(this)
        rcv_record_feature.apply {
            adapter = mFeatureAdapter
            layoutManager = LinearLayoutManager(
                this@RecordActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(RecordItemDecoration(this@RecordActivity))
        }
        //어댑터 구현하기
        if (foodData.foodMeat2.isNullOrEmpty()) {
            mFeatureAdapter.data = listOf(
                FeatureData(foodData.foodDry),
                FeatureData(foodData.foodMeat1)
            )
        } else {
            mFeatureAdapter.data = listOf(
                FeatureData(foodData.foodDry),
                FeatureData(foodData.foodMeat1),
                FeatureData(foodData.foodMeat2!!)
            )
        }

        mFeatureAdapter.notifyDataSetChanged()
    }

}
