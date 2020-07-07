package com.sopt.ounce.record

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.activity_image_search.*

class ImageSearchActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        //SearchView 색깔 지
        val searchIcon = sv_search.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)


        val cancelIcon = sv_search.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)


        val textView = sv_search.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)

        //리사이클러뷰와 서치뷰 연
        rv_search.layoutManager = LinearLayoutManager(rv_search.context)
        rv_search.setHasFixedSize(true)
        getItemList()
    }

    private fun getItemList() {
        val listItem = ArrayList<String>()
        listItem.add("Abc")
        listItem.add("Bcd")
        listItem.add("cde")
        listItem.add("efg")
        listItem.add("hit")
        listItem.add("aqwf")
        listItem.add("xgwrg")
        listItem.add("Abc")
        listItem.add("xcghjrty")
        listItem.add("werysx")
        listItem.add("oasfap")
        adapter = RecyclerViewAdapter(listItem)
        rv_search.adapter = adapter
    }
}