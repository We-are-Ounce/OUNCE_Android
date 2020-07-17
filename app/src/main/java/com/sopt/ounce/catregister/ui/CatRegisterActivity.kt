package com.sopt.ounce.catregister.ui


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.catregister.adapter.CatViewPagerAdapter
import com.sopt.ounce.catregister.data.CatInfoData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.StatusObject
import com.sopt.ounce.util.customEnqueue
import com.sopt.ounce.util.showLog
import kotlinx.android.synthetic.main.activity_cat_register.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File

class CatRegisterActivity : AppCompatActivity() {
    private lateinit var mViewPagerAdapter: CatViewPagerAdapter
    private val mOunce = OunceServiceImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_register)

        // 상태바 아이콘 변경
        StatusObject.setStatusBar(this)
        //뷰페이저 설정
        initPager()

        //취소 버튼 누르면 액티비티 종료
        btn_catregister_cancle.setOnClickListener {
            finish()
        }

        //좌측 상단 뒤로가기 버튼 누를 시 뒤로 가기
        img_catregister_back.setOnClickListener {
            if (vp_catregister.currentItem == 1) {
                vp_catregister.currentItem -= 1
            } else {
                finish()
            }
        }

        //확인 버튼 누를 시 다음 페이지 이동
        btn_catregister_ok.setOnClickListener {
            if (vp_catregister.currentItem == 0) {
                vp_catregister.currentItem += 1
                buttonEnable(false)
            } else {
                settingDataMultiForm()

            }
        }


    }

    private fun settingDataMultiForm() {
        val PARSE_STRING = "text/plain"
        val pictureRb = settingImgMultiPart()
        val accessToken = EasySharedPreference.Companion.getString("accessToken", "")
        val name = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileName)
        val weight = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileWeight)
        val gender = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileGender)
        val neutral = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileNeutral)
        val age = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileAge)
        val info = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileInfo)

        mOunce.SERVICE.postCatProfile(
            token = accessToken,
            profileImg = pictureRb,
            body = hashMapOf<String, RequestBody>(
                "profileName" to name,
                "profileWeight" to weight,
                "profileGender" to gender,
                "profileNeutral" to neutral,
                "profileAge" to age,
                "profileInfo" to info
            )
        ).customEnqueue(
            onSuccess = {
                if (it.success) {
                    val intent = Intent(this, CatRegisterFinishActivity::class.java)
                    startActivity(intent)
                }
            },
            onError = {
                "ServerError".showLog("고양이 프로필 등록 에러 : ${it.code()}")
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun settingImgMultiPart(): MultipartBody.Part {
        val options = BitmapFactory.Options()
        val inputStream = contentResolver.openInputStream(CatInfoData.catProfileUri!!)!!
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val photoBody =
            RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

        return MultipartBody.Part.createFormData(
            "profileImg",
            File(CatInfoData.catProfileUri.toString()).name,
            photoBody
        )
    }

    private fun initPager() {
        mViewPagerAdapter = CatViewPagerAdapter(supportFragmentManager)
        vp_catregister.adapter = mViewPagerAdapter
        dot_catregister_indicator.setViewPager(vp_catregister)
        dot_catregister_indicator.dotsClickable = false
    }

    fun setImmToFragment(): InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager


    @Suppress("DEPRECATION")
    fun buttonEnable(enable: Boolean) {
        if (enable) {
            btn_catregister_ok.apply {
                isEnabled = true
                setTextColor(resources.getColor(R.color.white))
            }
        } else {
            btn_catregister_ok.apply {
                isEnabled = false
                setTextColor(resources.getColor(R.color.greyish_two))
            }
        }

    }


}

