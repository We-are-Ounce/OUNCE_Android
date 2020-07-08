package com.sopt.ounce.record

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R

class SearchCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_complete)

        //val myLayoutManager = GridLayoutManager(this,2)
        GridLayoutManager(this, 2)

    }
}