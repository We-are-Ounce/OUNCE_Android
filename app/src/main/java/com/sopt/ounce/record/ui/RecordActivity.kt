package com.sopt.ounce.record.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.ounce.R
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.FeatureAdapter
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.server.UserRecordService
import kotlinx.android.synthetic.main.activity_record.*
import kotlin.properties.Delegates

class RecordActivity : AppCompatActivity() {
    private lateinit var mFeatureAdapter: FeatureAdapter
    private var mTotal by Delegates.notNull<Int>()
    private var mFavor by Delegates.notNull<Int>()

    //서버 통신, 싱글톤 그대로 가져옴
    private val mRecordRequest = UserRecordService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
//        ratingBar.setStarFillDrawable(resources.getDrawable(R.drawable.ic_evaluate_full))
//        ratingBar.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_total_unselected))
//        ratingBar2.setStarFillDrawable(resources.getDrawable(R.drawable.ic_favorite_select))
//        ratingBar2.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_favorite_unselected))

        ratingBar.setOnRatingChangeListener {
            mTotal = it.toInt()
        }
        ratingBar2.setOnRatingChangeListener {
            mFavor = it.toInt()
        }

        initFeatureRcv()

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
