package com.sopt.ounce.record.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.record.data.RequestModifyData
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import kotlinx.android.synthetic.main.activity_record_modify.*
import kotlinx.android.synthetic.main.activity_record_modify.record_ear_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_eye_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_fur_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_vomit_btn

class RecordModifyActivity : AppCompatActivity() {


    //서비스 호출
    private val mModifyRequest = OunceServiceImpl

    //private val mDeleteData = OunceServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_modify)
        btn_record_popup.setOnClickListener {
            val popup = PopupMenu(this, btn_record_popup)
            popup.inflate(R.menu.record_popup)
            popup.setOnMenuItemClickListener {
                Toast.makeText(this, it.title,Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        record_eye_btn.setOnClickListener {
            if(it.isSelected){
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            }
            else{
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_ear_btn.setOnClickListener {
            if(it.isSelected){
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            }
            else{
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_fur_btn.setOnClickListener {
            if(it.isSelected){
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            }
            else{
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_vomit_btn.setOnClickListener {
            if(it.isSelected){
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            }
            else{
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }
        record_x_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            finish()
        }

        val accessToken: String = EasySharedPreference.Companion.getString("accessToken","")
        //리뷰 수정
        mModifyRequest.SERVICE.putUpdateReview(
            accessToken = accessToken,
            reviewIdx = 2,
            body = RequestModifyData(
                reviewRating = 3,
                reviewPrefer = 2,
                reviewInfo = "사료",
                reviewMemo = "메모",
                reviewStatus = 3,
                reviewSmell = 2,
                reviewEye = true,
                reviewEar = false,
                reviewHair = false,
                reviewVomit = false,
                createdAt = "sss",
                foodIdx = 2,
                profileIdx = 3
            )
        ).customEnqueue(
            onSuccess = {
                // 서버통신에 성공해서 데이터를 받아왔을 때
                Toast.makeText(this@RecordModifyActivity,"내가 쓴 리뷰 수정 성공",Toast.LENGTH_SHORT).show()
                finish()

            },
            onError = {
                Toast.makeText(this@RecordModifyActivity,"리뷰 수정 권한이 없습니다.",Toast.LENGTH_SHORT).show()
                finish()
            }
        )

        //리뷰 삭제
//        mDeleteData.SERVICE.deleteDataReview(accessToken).customEnqueue(
//            onSuccess = {
//                Toast.makeText(this@RecordModifyActivity, "내가 쓴 리뷰 삭제 성공", Toast.LENGTH_SHORT).show()
//                finish()
//            },
//            onError = {
//                Toast.makeText(this@RecordModifyActivity, "리뷰 삭제 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//            }
//
//        )

    }


}