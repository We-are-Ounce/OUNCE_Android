package com.sopt.ounce.record.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.server.OunceServiceImpl
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.activity_search_complete.*

class SearchCompleteActivity : AppCompatActivity() {

    private val mModifyRequest = OunceServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_complete)

        //val myLayoutManager = GridLayoutManager(this,2)
        //GridLayoutManager(this, 2)

        record_back_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            finish()
        }


    }
}