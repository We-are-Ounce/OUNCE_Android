package com.sopt.ounce.catregister.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.sopt.ounce.R
import com.sopt.ounce.catregister.CatRegisterActivity
import com.sopt.ounce.catregister.data.CatInfoData
import com.sopt.ounce.util.showLog
import com.sopt.ounce.util.textCheckListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_cat_profile_register.*
import kotlinx.android.synthetic.main.fragment_cat_profile_register.view.*
import kotlinx.android.synthetic.main.fragment_id.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


class CatProfileRegisterFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var v: View
    private lateinit var mImm: InputMethodManager
    private lateinit var mActivity: CatRegisterActivity
    private var mSelectedImage: Uri? = null

    private var mCatName: String = ""
    private var mCatInfo: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as CatRegisterActivity

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cat_profile_register, container, false)
        //키보드 내리기 + 키보드 감지
        settingMethodManager()
        observeKeyboard()

        v.img_cat_profile_add.setOnClickListener {
            settingPermission()
        }

        v.edt_catprofile_name.apply {
            textCheckListener {
                mCatName = it.toString()
                checkText()
            }
        }

        v.edt_catprofile_explain.apply {
            textCheckListener {
                mCatInfo = it.toString()
                checkText()
            }
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_catprofile.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_catprofile_name.windowToken, 0)
        }

    }

    private fun checkText() {
        "showProfile".showLog("name : ${mCatName}, info : ${mCatInfo}")
        if (mCatName.isNotEmpty() && mCatInfo.isNotEmpty() && mSelectedImage != null) {
            mActivity.buttonEnable(true)
        } else {
            mActivity.buttonEnable(false)
        }
    }

    private fun observeKeyboard() {
        activity?.let {
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow) {
                        v.edt_catprofile_name.clearFocus()
                        v.edt_catprofile_explain.clearFocus()
                    }

                }
        }

    }

    private fun settingMethodManager() {
        mImm = mActivity.setImmToFragment()
    }

    private fun settingPermission() {
        val permis = object : PermissionListener {
            override fun onPermissionGranted() {
                // 갤러리 호출
                callGallery()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            }
        }

        TedPermission.with(mContext)
            .setPermissionListener(permis)
            .setRationaleMessage("갤러리 호출을 위해 권한을 설정해 주세요.")
            .setDeniedMessage("갤러리 권한은 기기 시스템 설정에서 변경 할 수 있습니다.")
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).check()
    }

    private fun callGallery() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(4, 3)
            .start(mContext, this)
    }

    @SuppressLint("Recycle")
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                mSelectedImage = result.originalUri
                formatImg()
                Glide.with(this)
                    .load(mSelectedImage).thumbnail(0.1f).into(v.img_cat_profile)
            }
        }
    }

    private fun formatImg() {
        val options = BitmapFactory.Options()
        val inputStream: InputStream = mActivity.contentResolver.openInputStream(mSelectedImage!!)!!
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val photoBody =
            RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
        val pictureRb = MultipartBody.Part.createFormData(
            "image",
            File(mSelectedImage.toString()).name,
            photoBody
        )

        //이미지 정보 넣어주기
        CatInfoData.catProfile?.profileImg = pictureRb

        checkText()

    }


}