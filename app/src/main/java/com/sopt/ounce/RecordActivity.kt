package com.sopt.ounce

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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_record.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class RecordActivity : AppCompatActivity() {

    //카메라 권한
    val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val FLAG_PERM_CAMERA = 98
    val FLAG_PERM_STORAGE = 99

    val FLAG_REQ_CAMERA = 101
    val FLAG_REQ_STORAGE = 102



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        if(checkPermission(STORAGE_PERMISSION, FLAG_PERM_STORAGE)){
            setViews()
        }
    }
    fun setViews(){
        button_Gallery.setOnClickListener{
            openGallery()
        }
    }
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, FLAG_REQ_STORAGE)
    }

    fun newFileName() : String {
        val sdf = SimpleDateFormat("yyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())

        return "$filename.jpg"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                FLAG_REQ_CAMERA->{
                    if(data?.extras?.get("data") != null ){
                        val bitmap = data?.extras?.get("data") as Bitmap
                        //image_Preview.setImageURI(bitmap)
                        val uri  = saveImageFile(newFileName(), "image/jpg", bitmap)
                        image_Preview.setImageURI(uri)
                    }
                }
                FLAG_REQ_STORAGE -> {
                    val uri = data?.data
                    image_Preview.setImageURI(uri)
                }
            }
        }
    }
    fun saveImageFile(filename:String, mimeType:String, bitmap:Bitmap) : Uri? {
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            values.put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

        try{
            if(uri != null){
                var descriptor = contentResolver.openFileDescriptor(uri, "w")
                if(descriptor != null){
                    val fos = FileOutputStream(descriptor.fileDescriptor)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos.close()
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        values.clear()
                        values.put(MediaStore.Images.Media.IS_PENDING, 0)
                        contentResolver.update(uri, values, null, null)
                    }
                }
            }
        } catch (e:java.lang.Exception){
            Log.e("File", "error=${e.localizedMessage}")
        }
        return uri

    }


    fun checkPermission(permissions:Array<out String>, flag:Int):Boolean {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            for(permission in permissions){
                if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, permissions, flag)
                    return false
                }
            }
        }
        return true
    }
    //카메라 권한 승인
     override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            FLAG_PERM_STORAGE->{
                for (grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        finish()
                        return
                    }
                }
                setViews()
            }
            FLAG_PERM_CAMERA ->{
                for (grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        finish()
                    }
                }
            }
        }
    }


}