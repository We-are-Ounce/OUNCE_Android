package com.sopt.ounce.productreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.ounce.R
import com.sopt.ounce.util.StatusObject

class ProductReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_review)

        StatusObject.setStatusBar(this)
    }
}