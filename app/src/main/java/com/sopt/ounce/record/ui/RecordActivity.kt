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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.activity_record.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        ratingBar.setStarFillDrawable(resources.getDrawable(R.drawable.ic_evaluate_full))
        ratingBar.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_total_unselected))
        ratingBar2.setStarFillDrawable(resources.getDrawable(R.drawable.ic_favorite_select))
        ratingBar2.setStarEmptyDrawable(resources.getDrawable(R.drawable.ic_favorite_unselected))
        
    }
}