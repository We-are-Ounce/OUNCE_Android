package com.sopt.ounce.record.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.main.data.ResponseReviewData
import com.sopt.ounce.record.data.RequestModifyData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_record_modify.*
import kotlinx.android.synthetic.main.activity_record_modify.ratingBar
import kotlinx.android.synthetic.main.activity_record_modify.ratingBar2
import kotlinx.android.synthetic.main.activity_record_modify.record_ear_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_eye_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_fur_btn
import kotlinx.android.synthetic.main.activity_record_modify.record_vomit_btn
import kotlin.properties.Delegates

class RecordModifyActivity : AppCompatActivity() {


    //서비스 호출
    private val mModifyRequest = OunceServiceImpl
    private lateinit var foodData: ResponseReviewData.Data

    private var modify_Total = 0
    private var modify_Favor = 0

    private var modify_Status = 0
    private var modify_Smell = 0

    // 트러블 현상에 관한 변수
    private var modifyEye = 0
    private var modifyEar = 0
    private var modifyFur = 0
    private var modifyVomit = 0

    //private val mDeleteData = OunceServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_modify)

        val intent = intent
        foodData = intent.getSerializableExtra("foodItem") as ResponseReviewData.Data

        btn_record_popup.setOnClickListener {
            val popup = PopupMenu(this, btn_record_popup)
            popup.inflate(R.menu.record_popup)
            popup.setOnMenuItemClickListener {
                Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        //총점을 받아오는 리뷰 수정 클릭 리스너
        ratingBar.setOnRatingChangeListener {
            modify_Total = it.toInt()
            "RecordStatus".showLog("${it.toInt()}")
        }
        //기호도를 받아오는 리뷰 수정 클릭 리스너
        ratingBar2.setOnRatingChangeListener {
            modify_Favor = it.toInt()
            "RecordStatus".showLog("${it.toInt()}")
        }






        record_eye_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_ear_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_fur_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }

        record_vomit_btn.setOnClickListener {
            if (it.isSelected) {
                it.setBackgroundResource(R.drawable.trouble_full)
                it.isSelected = false
            } else {
                it.setBackgroundResource(R.drawable.trouble_empty)
                it.isSelected = true
            }
        }
        record_x_btn.setOnClickListener {
            finish()
        }

        val accessToken: String = EasySharedPreference.Companion.getString("accessToken", "")
        //리뷰 수정
        //리뷰 수정 데이터 불러오기


//        mModifyRequest.SERVICE.putUpdateReview(
//            accessToken = accessToken,
//            reviewIdx = 2,
//            body = RequestModifyData(
//                modify_Total,
//                modify_Favor,
//                editTextTextPersonName.text.toString(),
//                memo_edt2.text.toString(),
//                modify_Status,
//                modify_Smell,
//                modifyEye,
//                modifyEar,
//                modifyFur,
//                modifyVomit,
//                foodData.foodIdx,
//                EasySharedPreference.getInt("profileIdx", 1)
//            )
//        ).customEnqueue(
//        onSuccess = {
//            // 서버통신에 성공해서 데이터를 받아왔을 때
//            Toast.makeText(this@RecordModifyActivity, "내가 쓴 리뷰 수정 성공", Toast.LENGTH_SHORT).show()
//            finish()
//
//        },
//        onError = {
//            Toast.makeText(this@RecordModifyActivity, "리뷰 수정 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        )

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