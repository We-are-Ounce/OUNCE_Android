package com.sopt.ounce.record.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.sopt.ounce.R
import com.sopt.ounce.login.data.RequestReviewData
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.FeatureAdapter
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.server.UserRecordService
import com.sopt.ounce.util.customEnqueue
import kotlinx.android.synthetic.main.activity_record.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat
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

        mRecordRequest.service.postReviewAdd(
            RequestReviewData(
                reviewRating = mTotal,
                reviewPrefer = mFavor,
                reviewInfo = editTextTextPersonName.text.toString(),
                reviewMemo = memo_edt.text.toString()
            )
        ).customEnqueue(onSuccess = {
        })
    }
}
