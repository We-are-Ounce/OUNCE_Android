package com.sopt.ounce.record.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.login.data.RequestReviewData
import com.sopt.ounce.login.data.ResponseReviewData
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.FeatureAdapter
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.record.data.RecordSearchFoodData
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class RecordActivity : AppCompatActivity() {

    private lateinit var mFeatureAdapter: FeatureAdapter
    private var mTotal by Delegates.notNull<Int>()
    private var mFavor by Delegates.notNull<Int>()

    //서버 통신, 싱글톤 그대로 가져옴
    private val mRecordRequest = OunceServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
//        ratingBar.setStarFillDrawable(resources.getDrawable(R.drawable.ic_evaluate_full))
//        ratingBar.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_total_unselected))
//        ratingBar2.setStarFillDrawable(resources.getDrawable(R.drawable.ic_favorite_select))
//        ratingBar2.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_favorite_unselected))

        val intent = intent
        val foodData : RecordSearchFoodData = intent.getSerializableExtra("foodItem") as RecordSearchFoodData

        Glide.with(this).load(foodData.foodImg).into(image_Preview)
        textView.text = foodData.foodManu
        textView2.text = foodData.foodName
        val foodIdx = foodData.foodIdx

        ratingBar.setOnRatingChangeListener {
            mTotal = it.toInt()
        }
        ratingBar2.setOnRatingChangeListener {
            mFavor = it.toInt()
        }

        initFeatureRcv()

        record_button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var data = RequestReviewData(
                    1,
                    1,
                    "한줄리뷰",
                    "리뷰메모입니다",
                    1,
                    1,
                    0,
                    1,
                    1,
                    1,
                    1,
                    //EasySharedPreference.getInt("profileIdx",1)
                2

                )

                var data_test = RequestReviewData(
                    1,
                    1,
                    "메모",
                    "리뷰",
                    1,
                    0,
                    0,
                    1,
                    1,
                    1,
                    2,
                    3
                )


                val postAddReview = mRecordRequest.SERVICE.postAddReview(
                    EasySharedPreference.Companion.getString("accessToken",""),
                    data)

                EasySharedPreference.Companion.getString("accessToken","")

                //비동기통신에서 오류처리
                "Recprd - call".showLog("here0")
                postAddReview.enqueue(object : Callback<ResponseReviewData> {
                    override fun onFailure(call: Call<ResponseReviewData>, t: Throwable) {
                        Log.e("recordAdd-failure:",t.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseReviewData>,
                        response: Response<ResponseReviewData>
                    ) {
                        "Recprd - call".showLog("here1")
                        if(response.isSuccessful){
                            //성공했을 때 처리
                            if(response.body()!!.success){
                                "Recprd - success".showLog("here2")
                                Toast.makeText(this@RecordActivity,"리뷰 등록 성공입니다.",Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@RecordActivity,"필요한 값이 없습니다.",Toast.LENGTH_SHORT).show()
                                finish()
                            }

                        }
                    }


                })

            }

        })

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

        mFeatureAdapter.data = listOf(
            FeatureData("건식"),
            FeatureData("사슴")
        )
        mFeatureAdapter.notifyDataSetChanged()
    }

    //리뷰 등록
    private fun recordAddReview() {





    }
}
