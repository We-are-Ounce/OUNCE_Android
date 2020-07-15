package com.sopt.ounce.record.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.ounce.R
import com.sopt.ounce.record.adapter.ItemAdapter
import com.sopt.ounce.record.data.ItemData
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.RecyclerViewAdapter
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.searchmain.data.foodsearch.RequestFoodSearchData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import kotlinx.android.synthetic.main.activity_image_search.*

class ImageSearchActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    lateinit var mItemAdapter: ItemAdapter
    var mGoodsData = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        val searchIcon = record_search_sv.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)


        val cancelIcon = record_search_sv.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.GRAY)

9
        val textView = record_search_sv.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.DKGRAY)
        rv_record_search.layoutManager = LinearLayoutManager(rv_record_search.context)
        rv_record_search.setHasFixedSize(true)
        //getItemList()
        mItemAdapter = ItemAdapter(this)
        rv_record_item.adapter = mItemAdapter
        record_search_sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val ounce = OunceServiceImpl.SERVICE.postFoodSearch(
                    RequestFoodSearchData(
                        searchKeyword = query!!,
                        pageStart = 1,
                        pageEnd = 5
                    )
                )
                ounce.customEnqueue(
                    onSuccess = {
                        val mItemData = it.data
                        mItemAdapter.datas = mItemData as MutableList<FoodData>
                        mItemAdapter.notifyDataSetChanged()
                    },
                    onError = {

                    },
                    onFaile = {

                    }
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
//        mItemAdapter = ItemAdapter(this)
//        rv_record_item.adapter = mItemAdapter
        //getGoodsList()
    }

    private fun getItemList() {
        val listItem = ArrayList<String>()
        listItem.add("이현우")
        listItem.add("안드")
        listItem.add("빅보")
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

//    private fun getGoodsList(){
//        mGoodsData.apply {
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름 "
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//        }
//        mItemAdapter.datas = mGoodsData
//        mItemAdapter.notifyDataSetChanged()
//
//    }
}