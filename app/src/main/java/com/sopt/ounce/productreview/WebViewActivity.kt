package com.sopt.ounce.productreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val intent = intent
        val webUrl = intent.getStringExtra("webPage") as String
        wv_product_review_webview.webViewClient = WebViewClient()
        wv_product_review_webview.loadUrl(webUrl)
    }
}