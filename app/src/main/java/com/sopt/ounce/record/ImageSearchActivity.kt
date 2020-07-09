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
    lateinit var mItemAdapter: ItemAdapter
    var mGoodsData = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        val searchIcon = sv_record_search.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)


        val cancelIcon = sv_record_search.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)


        val textView = sv_record_search.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        rv_record_search.layoutManager = LinearLayoutManager(rv_record_search.context)
        rv_record_search.setHasFixedSize(true)
        rv_record_search.addItemDecoration(RecordItemDecoration())
        getItemList()

        mItemAdapter = ItemAdapter(this)
        rv_record_item.adapter = mItemAdapter
        getGoodsList()
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
        rv_record_search.adapter = adapter
    }

    private fun getGoodsList(){
        mGoodsData.apply {
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
            add(
                ItemData(
                    cardview_image = R.drawable.img_card_cat,
                    cardview_item =  "고양이",
                    cardview_itemname = "고양이 사료"
                )
            )
        }
        mItemAdapter.datas = mGoodsData
        mItemAdapter.notifyDataSetChanged()
    }
}